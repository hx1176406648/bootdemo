package com.test.bootdemo;

import lombok.Data;

/**
 * @createTime : 2020/06/22 9:07
 * @autho : hx
 * @describe : null
 */
@Data
public class JsonData {
    /**
     * 抄读时间
     */
    private String readingTime;
    /**
     * 读数
     */
    private String value;
}
