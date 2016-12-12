package com.snoweagle.console.rest.security;


import java.util.List;
import com.snoweagle.console.common.domain.system.Permission;
import com.snoweagle.console.common.domain.system.Role;
import com.snoweagle.console.common.domain.system.User;
import com.snoweagle.console.service.system.PermissionService;
import com.snoweagle.console.service.system.UserService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.mockito.Mockito.mock;


@Service
public class SystemAuthorizingRealm extends AuthorizingRealm {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private UserService userSerivce;

    /**
     * 认证回调函数, 登录时调用
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        // 校验用户名密码
        User user = userSerivce.queryUserByUserNameAndPasswd(token.getUsername(), new String(token.getPassword()));
        logger.info("登陆认证：用户：{}",token.getUsername(),user.getPassword());
        if (user != null) {
            logger.info("登陆认证成功！user：{}",user);
            return new SimpleAuthenticationInfo(user,user.getPassword(),getName());
        } else {
            logger.info("登陆认证失败！user：",user.toString());
            return null;
        }
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User user = (User) principals.getPrimaryPrincipal();
        if (user!=null) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            List<Permission> permissions =  permissionService.queryPermissionsByUserId(user.getUserId());

            for(Role role:user.getRoles()){
                info.addRole(role.getRoleName());
            }
            for (Permission permission : permissions) {
                if (StringUtils.isNotBlank(permission.getPermission())) {
                    info.addStringPermission(permission.getPermission());
                }
            }
            return info;
        } else {
            return null;
        }
    }


}
