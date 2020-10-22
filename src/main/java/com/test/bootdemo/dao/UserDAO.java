package com.test.bootdemo.dao;

import com.test.bootdemo.pojo.Item;
import com.test.bootdemo.pojo.User;
import com.test.bootdemo.query.TestQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDAO {
    List<User> userLogin(User user);

    void addUser(User user);

    List<Item> listInfo();

    void testQuery(TestQuery testQuery);

    void deleteTest(int id);
}
