package com.sun.xiaotian.demo.test.fastjosn;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class TestFastJson {

    public static void main(String[] args) {
        JSONObject nullObject = JSON.parseObject(null);
        System.out.println(nullObject);
        JSONObject emptyObject = JSON.parseObject("");
        System.out.println(emptyObject);
    }
}
