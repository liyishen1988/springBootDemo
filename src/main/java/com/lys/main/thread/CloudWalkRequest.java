package com.lys.main.thread;

import java.io.Serializable;

/**
 * Created by Administrator on 2020/5/28.
 */
public class CloudWalkRequest implements Serializable{
    private String img;
    private Integer getFace;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getGetFace() {
        return getFace;
    }

    public void setGetFace(Integer getFace) {
        this.getFace = getFace;
    }
}
