package com.snoweagle.console.rest.controller.admin;



import com.alibaba.fastjson.JSON;
import com.snoweagle.console.common.domain.system.Menu;
import com.snoweagle.console.common.domain.system.User;
import com.snoweagle.console.service.system.PermissionService;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.apache.shiro.subject.Subject;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;


/**
 * Created by wangshaobo on 2015/6/17.
 */
@Controller
@RequestMapping
public class LoginController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PermissionService permissionService;
    @RequestMapping(value = "/")
    public String logout(HttpServletRequest request,ModelMap modelMap) throws Exception {
            Subject currentUser = SecurityUtils.getSubject();
            if (currentUser.isAuthenticated()) {
                currentUser.logout();
            }
            modelMap.put("message","");
            logger.info("用户登出");
            return "/admin/login";
     }
    @RequestMapping(value = "/admin/signin",  method = RequestMethod.POST)
    public String signin(HttpServletRequest request ,ModelMap modelMap) throws Exception {
        String name = request.getParameter("inputName");
        String pwd = request.getParameter("inputPassword");
        //登出路径
        String respAdds = "/admin/login";
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(name,pwd);

        try {
            subject.login(token);
            logger.warn("认证通过");
            User user = (User) subject.getPrincipal();
            Session session = subject.getSession();
            List<Menu> menus = permissionService.findUserMenuTree(user.getUserId());
            session.setAttribute("menus", JSON.toJSON(menus));
            logger.info("菜单列表：{}",JSON.toJSON(menus));
            session.setAttribute("currentUser", user);
            session.setAttribute("loginDate",DateFormatUtils.format(new Date(),"yyyyMMdd HH:mm:ss"));
            respAdds="redirect:/index";
        }catch (Exception e){
            e.printStackTrace();
            logger.error("认证失败.{}",e);
            modelMap.put("message","认证失败，用户名密码错误");
        }

        return respAdds;
    }
    @RequestMapping(value = "/index",  method = RequestMethod.GET)
    public String redirctIndex(){
        return "/admin/index";
    }

    @RequestMapping(value = "/main",  method = RequestMethod.GET)
    public String redirctMain(){
        return "/admin/main";
    }
}
