package com.lys.baseJava.设计模式.抽象工厂模式;

import com.lys.baseJava.设计模式.service.impl.Blue;
import com.lys.baseJava.设计模式.service.*;
import com.lys.baseJava.设计模式.service.impl.*;

/**
 * 创建扩展了 AbstractFactory 的工厂类，基于给定的信息生成实体类的对象
 */
public class ColorFactory extends AbstractFactory {
    @Override
    Color getColor(String color) {
        if (color == null) {
            return null;
        }
        if (color.equalsIgnoreCase("RED")) {
            return new Red();
        } else if (color.equalsIgnoreCase("GREEN")) {
            return new Green();
        } else if (color.equalsIgnoreCase("BLUE")) {
            return new Blue();
        }
        return null;
    }

    @Override
    Shape getShape(String shape) {
        return null;
    }
}
