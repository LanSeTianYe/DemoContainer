package com.xiaotian.demo.test.pattern;

import com.alibaba.fastjson.JSON;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public class TestTwoPoint {

    private static final Pattern twoPointNumber = Pattern.compile("\\.");

    public static void main(String[] args) {
        System.out.println(twoPointNumber.matcher("0"));
        System.out.println(twoPointNumber.matcher("0"));
        System.out.println(twoPointNumber.matcher("0"));
        Byte b = new Byte("123");
        byte b1 = 'a';
        System.out.println(String.valueOf(b));
        System.out.println(b1);

        BigDecimal bigDecimal = BigDecimal.valueOf(1.234D);
        System.out.println(JSON.toJSONString(bigDecimal));
        System.out.println(JSON.toJSONString(bigDecimal.setScale(0, BigDecimal.ROUND_HALF_EVEN)));

    }

}
