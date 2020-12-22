package com.lys.baseJava.设计模式.impl;

/**
 * 创建实现 Item 接口的抽象类，该类提供了默认的功能
 * <p>
 * 汉堡（Burger），可以是素食汉堡（Veg Burger）或鸡肉汉堡（Chicken Burger）
 */
public abstract class Burger implements Item {

    @Override
    public Packing packing() {
        return new Wrapper();
    }

    @Override
    public abstract float price();

}
