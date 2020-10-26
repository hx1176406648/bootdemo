package com.test.bootdemo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.test.bootdemo.dao.SmsDAO;
import com.test.bootdemo.pojo.Sms;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @createTime : 2020/10/12 13:21
 * @autho : hx
 * @describe : null
 */
@Controller
public class TestController {
    @Resource
    SmsDAO smsDAO;

    @GetMapping("/test")
    public String test(HttpServletRequest request){
        return request.getRemoteHost();
    }

    @GetMapping("/sms")
    public String sms(Model m, @RequestParam(value = "start",defaultValue = "0") int start, @RequestParam(value = "limit",defaultValue = "5") int limit){
        PageHelper.startPage(start,limit,"id desc");
        List<Sms> li = smsDAO.listInfo();
        PageInfo<Sms> page = new PageInfo<>(li);
        m.addAttribute("page",page);
        m.addAttribute("start",start);
        m.addAttribute("limit",limit);


        return "sms";
    }
}
