package com.lys.baseJava.设计模式.建造者模式;

import com.lys.baseJava.设计模式.pojo.Meal;

/**
 * BuiderPatternDemo 使用 MealBuider 来演示建造者模式（Builder Pattern ）
 */
public class BuiderPatternDemo {

    public static void main(String[] args) {
        MealBuilder mealBuilder = new MealBuilder();

        //点了套餐1：素食汉堡包+可口可乐
        Meal vegMeal = mealBuilder.prepareVegMeal();
        System.out.println("Veg Meal");
        vegMeal.showItems();
        System.out.println("Total Cost: " + vegMeal.getCost());

        //点了套餐2：鸡肉汉堡包+百事可乐
        Meal nonVegMeal = mealBuilder.prepareChickenMeal();
        System.out.println("Chicken Meal");
        nonVegMeal.showItems();
        System.out.println("Total Cost: " + nonVegMeal.getCost());
    }

}
