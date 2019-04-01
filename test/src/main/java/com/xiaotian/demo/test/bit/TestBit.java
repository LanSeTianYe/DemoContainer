package com.xiaotian.demo.test.bit;

public class TestBit {

    public static void main(String[] args) {
        System.out.println(String.format("%32s", Integer.toBinaryString(0)));
        System.out.println(String.format("%32s", Integer.toBinaryString(1)));
        System.out.println(String.format("%32s", Integer.toBinaryString(2)));
        System.out.println(String.format("%32s", Integer.toBinaryString(Integer.MAX_VALUE)));
        System.out.println(String.format("%32s", Integer.toBinaryString(-1)));
        System.out.println(String.format("%32s", Integer.toBinaryString(-2)));
        System.out.println(String.format("%32s", Integer.toBinaryString(-3)));
        System.out.println(String.format("%32s", Integer.toBinaryString(Integer.MIN_VALUE)));
    }

}
