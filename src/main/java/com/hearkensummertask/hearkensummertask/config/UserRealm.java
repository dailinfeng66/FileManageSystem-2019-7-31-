package com.hearkensummertask.hearkensummertask.config;


import com.hearkensummertask.hearkensummertask.bean.User;
import com.hearkensummertask.hearkensummertask.dao.UserDao;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class UserRealm extends AuthorizingRealm {

    /**
     * 执行授权逻辑
     *
     * @param principalCollection
     * @return
     */

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        //给资源进行授权
        /**
         * 这里有一点需要主要的是  有一个类个这个十分相似 不要搞错了
         * SimpleAuthorizationInfo  正确类
         * SimpleAuthenticationInfo  错误类   错误类里面没有添加授权字符串的方法
         */
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String userid = (String) request.getSession().getAttribute("userid");
        info.addStringPermission(userid);

        return info;
    }

    /**
     * 执行认证逻辑
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑");

//        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(token,token1.getPassword(),"");
//
//        return simpleAuthenticationInfo;
        return null;
    }
}
