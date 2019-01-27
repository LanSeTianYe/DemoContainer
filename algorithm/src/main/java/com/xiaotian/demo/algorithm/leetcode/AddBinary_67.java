package com.xiaotian.demo.algorithm.leetcode;

/**
 * <a href="https://leetcode.com/problems/add-binary/">Add Binary</a>
 */
public class AddBinary_67 {

    public String addBinary(String a, String b) {
        if (a.length() < b.length()) {
            return addBinary(b, a);
        }
        char[] aArray = a.toCharArray();
        char[] bArray = b.toCharArray();
        int indexA = a.length() - 1;
        int indexB = b.length() - 1;
        int max = Math.max(a.length(), b.length());
        int[] temp = new int[max + 1];
        while (indexB >= 0) {
            int value = temp[max] + (aArray[indexA] - '0') + (bArray[indexB] - '0');
            if (value > 1) {
                temp[max - 1] = 1;
            }
            temp[max] = value % 2;
            indexA--;
            indexB--;
            max--;
        }
        while (indexA >= 0) {
            int value = temp[max] + (aArray[indexA] - '0');
            if (value > 1) {
                temp[max - 1] = 1;
            }
            temp[max] = value % 2;
            max--;
            indexA--;
        }

        StringBuilder result = new StringBuilder();
        int start = temp[0] == 0 ? 1 : 0;
        for (int i = start; i < temp.length; i++) {
            result.append(temp[i]);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        AddBinary_67 addBinary_67 = new AddBinary_67();
        System.out.println(addBinary_67.addBinary("111", "00"));
        System.out.println(addBinary_67.addBinary("111", "1"));
        System.out.println(addBinary_67.addBinary("1", "111"));
        System.out.println(addBinary_67.addBinary("1010", "1011"));
    }
}
