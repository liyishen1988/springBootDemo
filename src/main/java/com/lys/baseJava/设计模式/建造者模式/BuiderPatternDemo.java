package com.lys.baseJava.设计模式.建造者模式;

import com.lys.baseJava.设计模式.impl.Meal;

/**
 * BuiderPatternDemo 使用 MealBuider 来演示建造者模式（Builder Pattern ）
 */
public class BuiderPatternDemo {

    public static void main(String[] args) {
        MealBuilder mealBuilder = new MealBuilder();

        Meal vegMeal = mealBuilder.prepareVegMeal();
        System.out.println("Veg Meal");
        vegMeal.showItems();
        System.out.println("Total Cost: " + vegMeal.getCost());

        Meal nonVegMeal = mealBuilder.prepareNonVegMeal();
        System.out.println("\n\nNon-Veg Meal");
        nonVegMeal.showItems();
        System.out.println("Total Cost: " + nonVegMeal.getCost());
    }

}
