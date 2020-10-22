package com.test.bootdemo.pojo;

import lombok.Data;

@Data
public class Item {
    private int id;
    private String name;
    private int price;
    private int count;
    private int maxLimit;
}
