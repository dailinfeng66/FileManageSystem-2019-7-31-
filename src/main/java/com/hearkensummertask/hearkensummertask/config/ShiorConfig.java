package com.hearkensummertask.hearkensummertask.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiorConfig {

    /**
     * 创建SHiorFilterFactoryBean
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
//            for (int i=0;i<90;i++)
//        System.out.println("进来了     ");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        //添加shiro内置过滤器
        /**
         * 常用过滤器,可以实现权限相关的拦截器
         *  anon: 无需认证(登录)   可以访问
         *  authc: 必须认证才可以访问
         *  user: 如果使用remmberme的功能可以直接访问
         *  perms: 该资源必须得到资源权限才可以访问
         *  role:  该资源必须得到角色权限才可以访问
         */


        /**
         * 1、下述代码中必须是LinkedHashMap 而不能是HashMap。
         *
         * 2、anon定义必须在authc之前
         *
         * 否则anon定义不生效
         */
        //设置拦截的请求

        //必须为LinkedHashMap 否则anon不生效
        Map<String, String> filterMap = new LinkedHashMap<>();
        /**
         * 不拦截的请求
         */

        /**
         * 拦截的请求
         */
        filterMap.put("/**","anon");

        shiroFilterFactoryBean.setLoginUrl("/index");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        return shiroFilterFactoryBean;
    }

    /**
     * 创建DefaultWebSecurityManager
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //关联realm

        defaultWebSecurityManager.setRealm(userRealm);
        return defaultWebSecurityManager;
    }

    /**
     * 创建realm
     */
    @Bean(name = "userRealm")   //bean 的意思是把这个方法放在容器中以便于上面的方法使用
    public UserRealm getRealm() {
        return new UserRealm();
    }


    /**
     * 配置一个shirodialect 用户thymeleaf和shiro标签配合使用
     */
    @Bean
    public ShiroDialect getShiroDialect() {
        return new ShiroDialect();
    }




}
