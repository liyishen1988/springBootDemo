package com.lys.baseJava.设计模式.抽象工厂模式;

import com.lys.baseJava.设计模式.impl.*;

/**
 * 创建扩展了 AbstractFactory 的工厂类，基于给定的信息生成实体类的对象
 */
public class ShapeFactory extends AbstractFactory {


    @Override
    Color getColor(String color) {
        return null;
    }

    @Override
    Shape getShape(String shapeType) {
        if (shapeType == null) {
            return null;
        }
        if (shapeType.equalsIgnoreCase("CIRCLE")) {
            return new Circle();
        } else if (shapeType.equalsIgnoreCase("RECTANGLE")) {
            return new Rectangle();
        } else if (shapeType.equalsIgnoreCase("SQUARE")) {
            return new Square();
        }
        return null;
    }
}
