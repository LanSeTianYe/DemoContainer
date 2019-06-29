package com.xiaotian.demo.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 测试空的对象相关代码
 */
public class TestNull {

    public static void main(String[] args) {
        testNullList();
    }

    public static void testNullList() {
        List<String> nullList = null;

        Date date = new Date(0);
        System.out.println(date);

        // NullPointerException
        try {
            for (String s : nullList) {
                System.out.println(s);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        try {
            // NullPointerException
            nullList.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            ArrayList<Object> emptyList = new ArrayList<>();
            emptyList.forEach(o -> {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
