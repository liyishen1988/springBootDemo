package com.lys.baseJava.设计模式.impl;

/**
 * 创建扩展了 Burger 和 ColdDrink 的实体类
 *
 * 鸡肉汉堡
 */
public class ChickenBurger extends Burger {
    @Override
    public String name() {
        return "Chicken Burger";
    }

    @Override
    public float price() {
        return 50.5f;
    }
}
