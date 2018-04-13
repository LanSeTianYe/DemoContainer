package com.sun.xiaotian.demo.test.guava;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class CollectionDemo {

    enum SexEnum {Mam, Woman}

    public static void main(String[] args) {
        testLists();
        testMaps();
    }

    private static void testLists() {
        ArrayList<String> strings = Lists.newArrayList(Splitter.on(",").omitEmptyStrings().trimResults().split("a,b,c,d,e"));
        System.out.println(strings);

        List<List<String>> partition = Lists.partition(strings, 1);
        for (List<String> stringList : partition) {
            System.out.println(stringList);
        }
    }

    private static void testMaps() {
        Map<Object, Object> nameMap = Maps.newHashMap();
        nameMap.put("132", "12312");
        Map<SexEnum, Object> enumMap = Maps.newEnumMap(SexEnum.class);
        enumMap.put(SexEnum.Mam, "123");
        enumMap.put(SexEnum.Woman, "456");
        enumMap.put(SexEnum.Woman, "456");
    }
}
