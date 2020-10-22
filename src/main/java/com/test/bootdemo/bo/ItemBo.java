package com.test.bootdemo.bo;

import com.test.bootdemo.pojo.Item;

import java.util.List;

public interface ItemBo {
    List<Item> listInfo();      //查询信息

    void addInfo(Item item);     //添加信息

    Item getOne(int id);        //查询单一信息

    void updateInfo(Item item);     //更新信息

    void deleteInfo(int id);        //删除信息
}
