package com.test.bootdemo.bo;

import com.test.bootdemo.pojo.User;

public interface UserBo {
    boolean userLogin(User user);       //登录
    void addUser(User user);        //注册
}
