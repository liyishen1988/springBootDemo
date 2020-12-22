package com.lys.baseJava.设计模式.建造者模式;

import com.lys.baseJava.设计模式.impl.*;

/**
 * 创建一个 MealBuilder 类，实际的 builder 类负责创建 Meal 对象
 *
 * 创建各种套餐组合
 */
public class MealBuilder {

    //套餐1：素食汉堡包+可口可乐
    public Meal prepareVegMeal (){
        Meal meal = new Meal();
        meal.addItem(new VegBurger());
        meal.addItem(new Coke());
        return meal;
    }

    //套餐2：鸡肉汉堡包+百事可乐
    public Meal prepareChickenMeal(){
        Meal meal = new Meal();
        meal.addItem(new ChickenBurger());
        meal.addItem(new Pepsi());
        return meal;
    }

}
