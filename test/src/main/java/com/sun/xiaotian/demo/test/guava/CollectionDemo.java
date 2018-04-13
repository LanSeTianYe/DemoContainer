package com.sun.xiaotian.demo.test.guava;

import com.google.common.collect.Maps;

import java.util.EnumMap;
import java.util.Map;

public class CollectionDemo {

    public static void main(String[] args) {
        Map<Object, Object> nameMap = Maps.newHashMap();
        nameMap.put("132", "12312");
        Map<SexEnum, Object> enumMap = Maps.newEnumMap(SexEnum.class);
        enumMap.put(SexEnum.Mam, "123");
        enumMap.put(SexEnum.Woman, "456");
        enumMap.put(SexEnum.Woman, "456");
    }
    enum SexEnum {
        Mam, Woman
    }
}
