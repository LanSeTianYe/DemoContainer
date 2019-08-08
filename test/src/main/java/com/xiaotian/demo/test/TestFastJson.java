package com.xiaotian.demo.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class TestFastJson {

    public static void main(String[] args) {
        Map<String, String> map1 = new LinkedHashMap<>();
        map1.put("1", "2");
        map1.put("4", null);
        map1.put("2", "3");

        Map<String, String> map2 = new LinkedHashMap<>();
        map2.put("2", "3");
        map2.put("1", "2");
        map2.put("4", null);


        System.out.println(JSON.toJSONString(map1));
        System.out.println(JSON.toJSONString(map2));
        System.out.println();
        System.out.println(JSON.toJSONString(new HashMap<>(map1)));
        System.out.println(JSON.toJSONString(new HashMap<>(map1)));
        System.out.println();
        System.out.println(JSON.toJSONString(new HashMap<>(map1), SerializerFeature.MapSortField));
        System.out.println(JSON.toJSONString(new HashMap<>(map2), SerializerFeature.MapSortField));
        System.out.println();
        System.out.println(JSON.toJSONString(new HashMap<>(map2), SerializerFeature.SortField));
        System.out.println(JSON.toJSONString(new HashMap<>(map1), SerializerFeature.SortField));
        System.out.println();


        JSONObject o1 = new JSONObject();
        JSONObject o2 = new JSONObject();

        o1.put("1", "2");
        o1.put("3", "4");

        o2.put("3", "4");
        o2.put("1", "2");


        System.out.println(JSON.toJSONString(o1));
        System.out.println(JSON.toJSONString(o2));
        System.out.println();
        System.out.println(JSON.toJSONString(o1, SerializerFeature.MapSortField));
        System.out.println(JSON.toJSONString(o2, SerializerFeature.MapSortField));
        System.out.println();
        System.out.println(JSON.toJSONString(o1, SerializerFeature.SortField));
        System.out.println(JSON.toJSONString(o2, SerializerFeature.SortField));
    }
}
