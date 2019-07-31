package com.hearkensummertask.hearkensummertask.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration

//springboot的拦截器配置
public class MymvcConfig implements WebMvcConfigurer {
    /**
     * 配置拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns(
                "/index",
                "/justCheckCode",
                "/",
                "/getCheckCode",
                "/login",
                "/static/**",
                "/judgeuserid",
                "/websocket",
                "/testweb",
                "/register",
                "/activate",
                "/toPhone",
                "/Sendsms",
                "/phonelogin",
                "/edit2"
        );
    }
}
