package com.xiaotian.demo.test.math;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TestBigDecimal {

    public static void main(String[] args) {

        {
            BigDecimal a = BigDecimal.valueOf(0.1212D);
            BigDecimal b = new BigDecimal("1.2121");
            BigDecimal c = new BigDecimal("2");
            BigDecimal d = new BigDecimal("5");


            System.out.println(a.add(b));
            System.out.println(a.subtract(b));
            System.out.println(a.multiply(b));
            System.out.println(a.divide(c, 3, RoundingMode.FLOOR));
            System.out.println(a.divide(c, 3, RoundingMode.CEILING));

            System.out.println(a.negate());
            System.out.println(a.negate().plus());

            System.out.println(a.max(b));
            System.out.println(d.remainder(c));
            System.out.println(c.remainder(d));
            //精度
            System.out.println(a.ulp());
        }
    }
}
