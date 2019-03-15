package com.xiaotian.demo.test;

public class TestFor {

    public static void main(String[] args) {
        int i = 0;
        for (charToInt('A'); (i < 2 && charToInt('B')); charToInt('C')) {
            i++;
            charToInt('D');
        }
    }

    private static boolean charToInt(char c) {
        System.out.println(c);
        return true;
    }
}
