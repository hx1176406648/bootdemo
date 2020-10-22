package com.test.bootdemo.config;

import com.test.bootdemo.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
     public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("拦截器配置完毕");
         registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/user/login");
    }
}
