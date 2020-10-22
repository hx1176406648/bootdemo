package com.test.bootdemo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.test.bootdemo.bo.ItemBo;
import com.test.bootdemo.pojo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 物品表的增删改查--resultful风格
 */

@Controller
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemBo itemBo;

    @GetMapping         //信息查询
    public String listInfo(Model m, @RequestParam(value = "start",defaultValue = "0") int start, @RequestParam(value = "limit",defaultValue = "5") int limit){
        PageHelper.startPage(start,limit,"id desc");
        List<Item> li = itemBo.listInfo();
        PageInfo<Item> page = new PageInfo<>(li);
        m.addAttribute("page",page);
        m.addAttribute("start",start);
        m.addAttribute("limit",limit);
        return "index";
    }

    @PostMapping        //信息添加
    public String addInfo(Item item){
        itemBo.addInfo(item);
        return "redirect:/item";
    }

    @PutMapping         //信息修改
    public String updateInfo(Item item){
        itemBo.updateInfo(item);
        return "redirect:/item";
    }

    @DeleteMapping("/{id}")      //信息删除
    public String deleteInfo(Item item){
        itemBo.deleteInfo(item.getId());
        return "redirect:/item";
    }

    @GetMapping("/{id}")
    public String editInfo(@PathVariable("id") int id,Model m){
        Item item = itemBo.getOne(id);
        m.addAttribute("item",item);
        return "editInfo";
    }
}
