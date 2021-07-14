package com.lys.baseJava.枚举;

import java.io.Serializable;

/**
 * 枚举
 */
public enum ColorEnum implements Serializable {

    RED("红色", 1), GREEN("绿色", 2), BLANK("白色", 3), YELLO("黄色", 4);

    private String name;

    private int code;

    private ColorEnum(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public static String getName(int code) {
        for (ColorEnum c : ColorEnum.values()) {
            if (c.getCode() == code) {
                return c.name;
            }
        }
        return null;
    }

    // get set 方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    //覆盖方法
    @Override
    public String toString() {
        return this.code + "_" + this.name;
    }
}
