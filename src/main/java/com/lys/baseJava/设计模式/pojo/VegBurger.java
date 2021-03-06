package com.lys.baseJava.设计模式.pojo;

import com.lys.baseJava.设计模式.service.impl.Burger;

/**
 * 创建扩展了 Burger 和 ColdDrink 的实体类
 *
 * 素食汉堡
 */
public class VegBurger extends Burger {

    @Override
    public String name() {
        return "Veg Burger";
    }

    @Override
    public float price() {
        return 25.0f;
    }
}
