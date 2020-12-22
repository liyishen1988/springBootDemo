package com.lys.baseJava.设计模式.impl;

/**
 * 创建扩展了 Burger 和 ColdDrink 的实体类
 *
 * 可口可乐
 */
public class Coke extends ColdDrink  {
    @Override
    public String name() {
        return "Coke";
    }

    @Override
    public float price() {
        return 30.0f;
    }
}
