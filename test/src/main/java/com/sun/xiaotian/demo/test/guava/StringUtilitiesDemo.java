package com.sun.xiaotian.demo.test.guava;

import com.google.common.base.*;

import java.io.UnsupportedEncodingException;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 */
public class StringUtilitiesDemo {

    public static void main(String[] args) {
        testJoiner();
        testSplitter();
        testCharsets();
        testCaseFormat();
    }

    private static void testJoiner() {
        //q_b_c
        String join = Joiner.on("_").skipNulls().join("q", null, "b", "c");
        System.out.println(join);
    }

    private static void testSplitter() {
        String originalString = " f o o  , b a r , ,,   q u x ";

        //[ f o o  ,  b a r ,  , ,    q u x ]
        System.out.println(Splitter.on(',').split(originalString));
        //[ f o o  ,  b a r ,  ,    q u x ]
        System.out.println(Splitter.on(',').omitEmptyStrings().split(originalString));
        //[f o o, b a r, q u x]
        System.out.println(Splitter.on(',').trimResults().omitEmptyStrings().split(originalString));

        String containsNumberStr = "a13a24b4a5b6";
        System.out.println(Splitter.onPattern("\\d").trimResults().omitEmptyStrings().split(containsNumberStr));
        //[a1, 3a, 24, b4, a5, b6]
        System.out.println(Splitter.fixedLength(2).trimResults().omitEmptyStrings().split(containsNumberStr));
        //[a1, 3a, 24b4a5b6]
        System.out.println(Splitter.fixedLength(2).trimResults().omitEmptyStrings().limit(3).split(containsNumberStr));
        //[a, a, b4a5b6]
        System.out.println(Splitter.on(CharMatcher.inRange('0', '9')).trimResults().omitEmptyStrings().limit(3).split(containsNumberStr));
        //[a13a24, 4a5, 6]?
        System.out.println(Splitter.on(CharMatcher.inRange('0', '9').removeFrom("a")).trimResults().omitEmptyStrings().split(containsNumberStr));
    }

    private static void testCharsets() {
        //110
        System.out.println("name".getBytes(Charsets.UTF_8)[0]);
    }

    private static void testCaseFormat() {
        //my name is xiaotian
        System.out.println(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "My Name Is XiaoTian"));
    }
}
