package com.snoweagle.console.rest.controller.system;

import com.snoweagle.console.common.domain.order.OrderRequest;
import com.snoweagle.console.common.domain.pagination.PageResult;
import com.snoweagle.console.service.order.OrderQueryService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by wangshaobo on 2015/6/17.
 */
@Controller
@RequestMapping(value = "/system/permission")
public class PermissionController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    OrderQueryService orderQueryService;

    @RequestMapping(value = "/init")
    public String init(ModelMap modelMap) {
        return "/system/sysPermission";

    }
    @RequestMapping(value = "/query",method = RequestMethod.GET)
    @ResponseBody
    public PageResult query(@ModelAttribute OrderRequest request) throws Exception {

        logger.warn("接收请求参数：{}" , request.toString());
        try {
           Subject currentUser = SecurityUtils.getSubject();
           currentUser.checkPermission("instOrder:query");
           return doQuery(request);

        }catch (Exception e){
            logger.error("系统异常:{}",e.getMessage(),e);
            return null;
        }

    }



    private  PageResult doQuery(OrderRequest orderRequest){

        //get all
        PageResult result = orderQueryService.queryByDynamicParams(orderRequest);
        return  result;
    }

}
