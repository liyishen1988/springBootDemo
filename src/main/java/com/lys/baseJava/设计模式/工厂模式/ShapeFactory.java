package com.lys.baseJava.设计模式.工厂模式;

import com.lys.baseJava.设计模式.service.impl.Circle;
import com.lys.baseJava.设计模式.service.impl.Rectangle;
import com.lys.baseJava.设计模式.service.Shape;
import com.lys.baseJava.设计模式.service.impl.Square;

/**
 * 创建一个工厂，生成基于给定信息的实体类的对象
 */
public class ShapeFactory {

    //使用 getShape 方法获取形状类型的对象
    public Shape getShape(String shapeType){
        if(shapeType == null){
            return null;
        }
        if(shapeType.equalsIgnoreCase("CIRCLE")){
            return new Circle();
        } else if(shapeType.equalsIgnoreCase("RECTANGLE")){
            return new Rectangle();
        } else if(shapeType.equalsIgnoreCase("SQUARE")){
            return new Square();
        }
        return null;
    }

}
