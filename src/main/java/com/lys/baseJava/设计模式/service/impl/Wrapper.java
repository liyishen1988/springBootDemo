package com.lys.baseJava.设计模式.service.impl;

import com.lys.baseJava.设计模式.service.Packing;

/**
 * 创建实现 Packing 接口的实体类
 */
public class Wrapper implements Packing {
    @Override
    public String pack() {
        return "Wrapper";
    }
}
