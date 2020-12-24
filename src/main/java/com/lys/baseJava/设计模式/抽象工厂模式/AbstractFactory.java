package com.lys.baseJava.设计模式.抽象工厂模式;

import com.lys.baseJava.设计模式.service.Color;
import com.lys.baseJava.设计模式.service.Shape;

/**
 * 为 Color 和 Shape 对象创建抽象类来获取工厂
 */
public abstract class AbstractFactory {

    abstract Color getColor(String color);

    abstract Shape getShape(String shape);

}
