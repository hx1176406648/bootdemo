package com.test.bootdemo.bo.impl;

import com.test.bootdemo.bo.UserBo;
import com.test.bootdemo.dao.UserDAO;
import com.test.bootdemo.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserBoImpl implements UserBo {
    @Resource
    private UserDAO userDAO;

    @Override
    public boolean userLogin(User user) {
        System.out.println(user);
        List<User> userList = userDAO.userLogin(user);
        if(userList.isEmpty())  return false;
        else return true;
    }

    @Override
    public void addUser(User user) {
        userDAO.addUser(user);
    }
}
