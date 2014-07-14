package com.ahpu.utils;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.ahpu.bean.User;
import com.ahpu.intf.IUser;

/*
 * 用户授权与认证
 */
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    IUser userService;
    
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) principals.fromRealm(getName()).iterator().next();
        if(username != null){
            User user = userService.queryByName(username);
            if(user != null && user.getRole() != null){
                SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
                info.addRole(user.getRole());
            }
        }
        return null;
    }

    // 获取认证信息
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
            throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String username = token.getUsername();
        if(username != null && !"".equals(username)){
            User user = userService.queryByName(username);
            if(user != null){
                return new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(),getName());
            }
        }
        return null;
    }

}
