package com.lys.baseJava.设计模式.建造者模式;

import com.lys.baseJava.设计模式.impl.*;

/**
 * 创建一个 MealBuilder 类，实际的 builder 类负责创建 Meal 对象
 */
public class MealBuilder {

    public Meal prepareVegMeal (){
        Meal meal = new Meal();
        meal.addItem(new VegBurger());
        meal.addItem(new Coke());
        return meal;
    }

    public Meal prepareNonVegMeal (){
        Meal meal = new Meal();
        meal.addItem(new ChickenBurger());
        meal.addItem(new Pepsi());
        return meal;
    }

}
