package com.xiaotian.demo.test.fastjosn;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class TestFastJson {

    public static void main(String[] args) {
        JSONObject nullObject = JSON.parseObject(null);
        System.out.println(nullObject);
        JSONObject emptyObject = JSON.parseObject("");
        System.out.println(emptyObject);
        JSONUser jsonUser = JSON.parseObject("{}", JSONUser.class);
        System.out.println(jsonUser);
    }
    
    static class JSONUser {

        String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
