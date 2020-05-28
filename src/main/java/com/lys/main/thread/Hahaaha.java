package com.lys.main.thread;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by Administrator on 2020/5/28.
 */
public class Hahaaha {
    public static void main(String[] args) {
        CloudWalkRequest request = new CloudWalkRequest();
        request.setImg("xxxx");
        request.setGetFace(1);
        String postString = JSONObject.toJSONString(request);
    }
}
