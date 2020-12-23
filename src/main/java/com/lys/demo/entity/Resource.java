package com.lys.demo.entity;

import java.io.Serializable;
import java.util.Date;

public class Resource implements Serializable{

    private long id;
    private long userId;
    private String path;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
