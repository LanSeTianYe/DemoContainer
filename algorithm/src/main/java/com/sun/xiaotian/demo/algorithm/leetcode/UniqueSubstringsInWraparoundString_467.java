package com.sun.xiaotian.demo.algorithm.leetcode;


import java.util.HashSet;
import java.util.Set;

public class UniqueSubstringsInWraparoundString_467 {

    public int findSubstringInWraproundString(String p) {
        if (p.length() <= 1) {
            return p.length();
        }
        Set<String> subStr = new HashSet<>();

        int start = 0;
        for (int i = 1; i < p.length(); i++) {
            if (!(p.charAt(i) == p.charAt(i - 1) + 1 || (p.charAt(i) == 'a' && p.charAt(i - 1) == 'z'))) {
                subStr(p.substring(start, i), subStr);
                start = i;
            }
        }
        subStr(p.substring(start), subStr);
        return subStr.size();
    }

    private void subStr(String str, Set<String> resultStr) {
        if (resultStr.contains(str)) {
            return;
        }
        resultStr.add(str);
        for (int i = 1; i < str.length(); i++) {
            for (int j = 0; i + j <= str.length(); j++) {
                resultStr.add(str.substring(j, j + i));
            }
        }
    }

    public static void main(String[] args) {
        UniqueSubstringsInWraparoundString_467 uniqueSubstringsInWraparoundString_467 = new UniqueSubstringsInWraparoundString_467();
        System.out.println(uniqueSubstringsInWraparoundString_467.findSubstringInWraproundString("a"));
        System.out.println(uniqueSubstringsInWraparoundString_467.findSubstringInWraproundString("zab"));
        System.out.println(uniqueSubstringsInWraparoundString_467.findSubstringInWraproundString("cac"));
        System.out.println(uniqueSubstringsInWraparoundString_467.findSubstringInWraproundString("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz"));
    }
}
