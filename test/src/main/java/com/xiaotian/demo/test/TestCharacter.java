package com.xiaotian.demo.test;

public class TestCharacter {

    public static void main(String[] args) {
        //ASCII
        printChar(0, 32);
        printChar(33, 47);
        printChar(48, 57);
        printChar(58, 64);
        printChar(65, 90);
        printChar(91, 96);
        printChar(97, 122);
        printChar(123, 128);
    }

    private static void printChar(int start, int end) {
        System.out.println();
        System.out.print(String.format("[%s,%s]:\t  ", start, end));
        System.out.print("[");
        String split = "";
        for (int i = start; i <= end; i++) {
            System.out.print(split + "" + ((char) i));
            split = " ";
        }
        System.out.print("]");
    }
}
