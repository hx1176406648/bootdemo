package com.test.bootdemo.bo.impl;

import com.test.bootdemo.bo.ItemBo;
import com.test.bootdemo.dao.ItemDAO;
import com.test.bootdemo.pojo.Item;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ItemBoImpl implements ItemBo {
    @Resource
    private ItemDAO itemDAO;

    @Override
    public List<Item> listInfo() {
        return itemDAO.listInfo();
    }

    @Override
    public void addInfo(Item item) {
        itemDAO.addInfo(item);
    }

    @Override
    public Item getOne(int id) {
        return itemDAO.getOne(id);
    }

    @Override
    public void updateInfo(Item item) {
        itemDAO.updateInfo(item);
    }

    @Override
    public void deleteInfo(int id) {
        itemDAO.deleteInfo(id);
    }
}
