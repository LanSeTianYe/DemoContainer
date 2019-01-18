package com.xiaotian.demo.test;

public class TestInteger {

    public static void main(String[] args) {
        Integer a = new Integer(1);
        Integer b = new Integer(1);
        Integer c = Integer.valueOf(1);
        Integer d = Integer.valueOf(1);
        Integer e = 0;
        Integer f = 0;

        System.out.println(a == b);
        System.out.println(a == c);
        System.out.println(c == d);
        System.out.println(e == f);
    }
}
