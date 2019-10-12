package com.xiaotian.demo.test.math;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Objects;

public class TestBigDecimal {

    public static void main(String[] args) {
        BigDecimal a = BigDecimal.valueOf(1.1D);
        BigDecimal b = BigDecimal.valueOf(1.5D);
        testDoubleToBigDecimal();
        add(a, b);
    }

    public void scale(BigDecimal... value) {
        if (!Objects.isNull(value)) {
            Arrays.stream(value).map(v -> v.setScale(4, BigDecimal.ROUND_HALF_UP));
        }
    }

    private static void add(BigDecimal a, BigDecimal b) {

        BigDecimal scale = a.add(b).setScale(3, BigDecimal.ROUND_HALF_UP);
        System.out.println(scale);
    }

    private static void testDoubleToBigDecimal() {
        double money = 11.11D;
        System.out.println(money);
        //直接使用构造函数会出现精度问题
        //11.1099999999999994315658113919198513031005859375
        System.out.println(new BigDecimal(money));
        //使用 BigDecimal.valueOf 构造不会出现精度问题
        //11.11
        System.out.println(BigDecimal.valueOf(money));
    }
}
