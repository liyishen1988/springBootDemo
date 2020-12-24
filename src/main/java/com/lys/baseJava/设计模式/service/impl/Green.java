package com.lys.baseJava.设计模式.service.impl;

import com.lys.baseJava.设计模式.service.Color;

/**
 * Created by Administrator on 2020/12/21.
 */
public class Green implements Color {
    @Override
    public void fill() {
        System.out.println("Inside Green::fill() method.");
    }
}
