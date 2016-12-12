package com.snoweagle.console.rest.controller.system;

import com.netfinworks.fc.channel.component.model.autocode.ChannelInterface;
import com.snoweagle.console.service.system.generator.ClassGeneratorService;
import com.snoweagle.console.service.system.generator.autocode.ChannelGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by wangshaobo on 2015/6/17.
 */
@Controller
@RequestMapping(value = "/system/classGenerator")
public class GeneratorController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    ClassGeneratorService generatorService;

    @RequestMapping(value = "/init")
    public String init(ModelMap modelMap) {
        modelMap.put("result", null);
        return "/system/genClass";

    }
    @RequestMapping(value = "/import",method = RequestMethod.POST)
    public String import0(@RequestParam(value = "file", required = false) MultipartFile file,HttpServletRequest request,ModelMap modelMap  )  {

        HttpSession session = request.getSession();
        session.removeAttribute("classGeneratorCache");

        try {
              Map map = generatorService.initializeFile( file.getBytes());
             session.setAttribute("classGeneratorCache",map);
            ChannelGenerator cg = (ChannelGenerator)map.get("I_generator");
            Map<String, ChannelInterface>  iif = cg.interFaces;
            modelMap.put("interfaceInfo",iif);

        }catch (Exception e){
            logger.error("系统异常:{}",e.getMessage(),e);
        }
        return "/system/genClass";
    }

    @RequestMapping(value = "/genClass")
    @ResponseBody
    public Map genClass(HttpServletRequest request)  {

        String code = request.getParameter("code");
        try {
            Map cache =(Map)request.getSession().getAttribute("classGeneratorCache");
            if(cache!=null){
                return generatorService.generatorClass(cache, code);
            }

        }catch (Exception e){
            logger.error("系统异常:{}",e.getMessage(),e);

        }
        return null;
    }

}
