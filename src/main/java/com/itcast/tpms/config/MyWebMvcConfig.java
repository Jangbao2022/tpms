package com.itcast.tpms.config;

import com.itcast.tpms.interceptor.LoginInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyWebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("profile");
        registry.addViewController("/index").setViewName("profile");
        registry.addViewController("/main").setViewName("profile");
        registry.addViewController("/page/login").setViewName("login");
        registry.addViewController("/page/blank").setViewName("blank");
        registry.addViewController("/page/profile").setViewName("profile");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludePathPatterns = new ArrayList<>();

        //登录页
        excludePathPatterns.add("/page/login");
        excludePathPatterns.add("/login");
        excludePathPatterns.add("/logon");

        //错误
        excludePathPatterns.add("/error");

        //静态资源
        excludePathPatterns.add("/assets/**");
        excludePathPatterns.add("/bootstrap/**");
        excludePathPatterns.add("/css/**");
        excludePathPatterns.add("/img/**");
        excludePathPatterns.add("/js/**");
        excludePathPatterns.add("/scss/**");


        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(excludePathPatterns);
    }
}
