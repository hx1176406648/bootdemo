package com.test.bootdemo.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CharEncodeFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        req.setCharacterEncoding("UTF-8");
  //      System.out.println("请求经过 字符编码过滤器");
        chain.doFilter(req,res);
  //      System.out.println("响应经过 字符编码过滤器");

    }

    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("字符编码过滤器随着服务器的启动一起启动");
    }
}
