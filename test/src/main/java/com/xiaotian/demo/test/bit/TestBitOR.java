package com.xiaotian.demo.test.bit;

public class TestBitOR {

    private static final int MAXIMUM_CAPACITY = Integer.MAX_VALUE - 8;

    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(127));
        System.out.println(Integer.toBinaryString(128));
        System.out.println(Integer.toBinaryString(129));
        System.out.println(tableSizeFor(127));
        System.out.println(tableSizeFor(128));
        System.out.println(tableSizeFor(129));
    }

    private static int tableSizeFor(int c) {
        int n = c - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
}
