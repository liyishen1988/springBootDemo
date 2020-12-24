package com.lys.baseJava.设计模式.service.impl;

import com.lys.baseJava.设计模式.service.Shape;

/**
 * 创建实现接口的实体类
 */
public class Circle implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}
