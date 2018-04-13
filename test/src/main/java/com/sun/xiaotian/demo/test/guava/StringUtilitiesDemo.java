package com.sun.xiaotian.demo.test.guava;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

/**
 * 字符串工具类
 */


public class StringUtilitiesDemo {

    public static void main(String[] args) {
        //q_b_c
        String join = Joiner.on("_").skipNulls().join("q", null, "b", "c", new StringUtilitiesDemo());
        System.out.println(join);

        //[f o o, b a r, q u x]
        System.out.println(Splitter.on(',').trimResults().omitEmptyStrings().split(" f o o  , b a r, ,   q u x "));
    }

    @Override
    public String toString() {
        return "StringUtilitiesDemo{}";
    }
}
