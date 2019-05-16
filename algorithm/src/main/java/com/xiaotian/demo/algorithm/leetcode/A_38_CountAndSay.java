package com.xiaotian.demo.algorithm.leetcode;

public class A_38_CountAndSay {

    public String countAndSay(int n) {
        if (n <= 0) {
            return "";
        }

        String result = "1";
        int index = 1;
        while (index < n) {
            result = getNext(result);
            index++;
        }
        return result;
    }

    private String getNext(String last) {
        StringBuilder result = new StringBuilder();
        char ch = 'a';
        int number = 0;
        for (char c : last.toCharArray()) {
            if (c == ch) {
                number++;
            } else {
                if (ch != 'a') {
                    result.append(number);
                    result.append(ch);
                }
                ch = c;
                number = 1;
            }
        }
        result.append(number);
        result.append(ch);
        return result.toString();
    }
}
