package com.test.bootdemo.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SessionFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        //访问登录注册页面放行
        if(uri.endsWith("login.html") || uri.endsWith("regis.html") || uri.endsWith("login")){
  //          System.out.println("请求经过 session类过滤器");
            chain.doFilter(req,res);
  //          System.out.println("响应经过 session类过滤器");
            return;
        }

        //访问静态资源文件放行
        if(uri.endsWith(".css") || uri.endsWith(".js")){
   //         System.out.println("请求经过 session类过滤器");
            chain.doFilter(req,res);
    //        System.out.println("响应经过 session类过滤器");
            return;
        }

        //判断用户是否已经登陆过
        if(null != req.getSession().getAttribute("user")){
      //      System.out.println("请求经过 session类过滤器");
            chain.doFilter(req,res);
      //      System.out.println("响应经过 session类过滤器");
            return;
        }else {
            res.sendRedirect(req.getContextPath() + "/login.html");
        }
    }

    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("session检验过滤器随着服务器的启动一起启动");
    }
}
