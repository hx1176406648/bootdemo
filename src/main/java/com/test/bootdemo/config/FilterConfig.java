package com.test.bootdemo.config;

import com.test.bootdemo.filter.*;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean registEncodeFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new CharEncodeFilter());
        registration.addUrlPatterns("/*");
        registration.setName("CharEncodeFilter");
        registration.setOrder(0);
        return registration;
    }

    //@Bean
    public FilterRegistrationBean registLogFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new LogUrlFilter());
        registration.addUrlPatterns("/*");
        registration.setName("LogUrlFilter");
        registration.setOrder(1);
        return registration;
    }

    //@Bean
    public FilterRegistrationBean registSessionFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new SessionFilter());
        registration.addUrlPatterns("/*");
        registration.setName("SessionFilter");
        registration.setOrder(2);
        return registration;
    }

    @Bean
    public FilterRegistrationBean registTest1Filter(){
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new TestFilter());
        registration.addUrlPatterns("/*");
        registration.setName("TestFilter");
        registration.setOrder(3);
        return registration;
    }

    @Bean
    public FilterRegistrationBean registTest2Filter(){
        System.out.println("过滤器配置完毕");
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new CheckFilter());
        registration.addUrlPatterns("/*");
        registration.setName("CheckFilter");
        registration.setOrder(4);
        return registration;
    }
}
