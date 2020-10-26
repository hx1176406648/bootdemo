package com.test.bootdemo.dao;

import com.test.bootdemo.pojo.Sms;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @createTime : 2020/10/23 12:42
 * @autho : hx
 * @describe : null
 */
@Mapper
public interface SmsDAO {
    List<Sms> listInfo();      //查询信息
}
