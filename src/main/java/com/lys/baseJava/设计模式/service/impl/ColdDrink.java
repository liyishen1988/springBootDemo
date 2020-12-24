package com.lys.baseJava.设计模式.service.impl;

import com.lys.baseJava.设计模式.service.Item;
import com.lys.baseJava.设计模式.service.Packing;

/**
 * 创建实现 Item 接口的抽象类，该类提供了默认的功能
 *
 * 冷饮（Cold drink），可以是可口可乐（coke）或百事可乐（pepsi）
 */
public abstract class ColdDrink implements Item {

    @Override
    public Packing packing() {
        return new Bottle();
    }

    @Override
    public abstract float price();
}
