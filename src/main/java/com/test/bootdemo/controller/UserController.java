package com.test.bootdemo.controller;

import com.test.bootdemo.bo.UserBo;
import com.test.bootdemo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * 用户的增删改查
 */

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserBo userBo;


    @RequestMapping("/add")         //注册
    public ModelAndView addUser(User user){
        userBo.addUser(user);

        ModelAndView mav = new ModelAndView("redirect:../login.html");
        return mav;
    }


    @RequestMapping("/login")             //登录
    public ModelAndView userLogin(User user,HttpSession httpSession){
        boolean flag = userBo.userLogin(user);

        ModelAndView mav;
        if(flag){
            httpSession.setAttribute("user",user.getUserName());
            mav = new ModelAndView("redirect:../index.html");
        }
        else{
            mav = new ModelAndView("redirect:../login.html");
        }

        return mav;
    }

    @RequestMapping("/index")       //网页跳转
    public ModelAndView index(){
        ModelAndView mav = new ModelAndView("redirect:/item");
        return mav;
    }
}
