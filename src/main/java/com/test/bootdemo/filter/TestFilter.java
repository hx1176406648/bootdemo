package com.test.bootdemo.filter;

import javax.servlet.*;
import java.io.IOException;


public class TestFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
     //   System.out.println("请求经过 测试类1过滤器");
        chain.doFilter(request,response);
      //  System.out.println("响应经过 测试类1过滤器");
    }

    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("测试过滤器启动");
    }
}
