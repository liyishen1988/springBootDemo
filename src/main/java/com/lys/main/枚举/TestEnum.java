package com.lys.main.枚举;

import java.io.Serializable;

/**
 * 枚举
 */
public enum TestEnum implements Serializable{

    SUCCESS(1,"成功"),

    FAILED(2,"失败"),

    UNKNOWN(3,"未知");

    private int code;
    private String message;

    TestEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
