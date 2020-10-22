package com.test.bootdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @createTime : 2020/10/12 13:21
 * @autho : hx
 * @describe : null
 */
@RestController
public class TestController {
    @GetMapping("/test")
    public String test(HttpServletRequest request){
        return request.getRemoteHost();
    }
}
