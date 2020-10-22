package com.test.bootdemo.pojo;
import lombok.Data;

@Data
public class User {
    private int id;
    private String userName;
    private String passWord;
    private String addr;
    private String phone;

    @Override
    public String toString(){
        return "账号 :" + userName + "密码 ：" + passWord;
    }
}
