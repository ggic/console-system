package com.snoweagle.console.rest.controller.system;

import com.snoweagle.console.common.domain.common.RestResponse;
import com.snoweagle.console.common.domain.pagination.PageResult;
import com.snoweagle.console.common.domain.system.User;
import com.snoweagle.console.rest.constants.ResponseStatus;
import com.snoweagle.console.service.system.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by wangshaobo on 2015/6/17.
 */
@Controller
@RequestMapping(value = "/system/user")
public class UserController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    UserService userService;

    @RequestMapping(value = "/init")
    public String init(ModelMap modelMap) {

        return "/system/user";

    }
    @RequestMapping(value = "/query",method = RequestMethod.GET)
    @ResponseBody
    public PageResult query(@ModelAttribute User request) throws Exception {

        logger.warn("接收请求参数：{}" , request.toString());
        try {
           Subject currentUser = SecurityUtils.getSubject();
           currentUser.checkPermission("system:user");
           return doQuery(request);
        }catch (Exception e){
            logger.error("系统异常:{}",e.getMessage(),e);
            return null;
        }

    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public RestResponse addUser(@ModelAttribute User user) throws Exception {

        logger.warn("接收请求参数：{}" , user.toString());
        RestResponse response = new RestResponse();        try {
            userService.addUser(user);
            response.setStatus(ResponseStatus.SUCCESS.getCode());

        }catch (Exception e){
            logger.error("系统异常:{}", e.getMessage(), e);
            response.setStatus(ResponseStatus.FAILURE.getCode());
            response.setStatus(e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public RestResponse show(@PathVariable Long id) throws Exception {

        logger.warn("接收请求参数：{}" ,id);
        RestResponse response = new RestResponse();
        try {
            response.setContent(userService.queryUserByPrimaryKey(id));
            response.setStatus( ResponseStatus.SUCCESS.getCode());

        }catch (Exception e){
            logger.error("系统异常:{}", e.getMessage(), e);
            response.setStatus( ResponseStatus.FAILURE.getCode());
            response.setStatus(e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ResponseBody
    public RestResponse edit(@ModelAttribute User user) throws Exception {

        logger.warn("接收请求参数：{}" , user.toString());
        RestResponse response = new RestResponse();
        try {
            userService.modifyUser(user);
            response.setStatus( ResponseStatus.SUCCESS.getCode());

        }catch (Exception e){
            logger.error("系统异常:{}", e.getMessage(), e);
            response.setStatus( ResponseStatus.FAILURE.getCode());
            response.setStatus(e.getMessage());
        }
        return response;
    }
    private  PageResult doQuery(User user){

        return  userService.queryUserPagesByDynamicParams(user);
    }

}
