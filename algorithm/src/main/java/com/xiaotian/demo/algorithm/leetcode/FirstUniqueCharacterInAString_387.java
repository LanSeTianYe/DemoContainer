package com.xiaotian.demo.algorithm.leetcode;

/**
 * <a href="https://leetcode.com/problems/first-unique-character-in-a-string/">First Unique Character in a String</a>
 */
public class FirstUniqueCharacterInAString_387 {

    public int firstUniqChar(String s) {
        int[] letterAndTimes = new int[256];
        byte[] bytes = s.getBytes();
        for (byte b : bytes) {
            letterAndTimes[b] = letterAndTimes[b] + 1;
        }
        for (int i = 0; i < s.length(); i++) {
            if (letterAndTimes[bytes[i]] == 1) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        FirstUniqueCharacterInAString_387 firstUniqueCharacterInAString_387 = new FirstUniqueCharacterInAString_387();
        System.out.println(firstUniqueCharacterInAString_387.firstUniqChar("abcdefgh"));
        System.out.println(firstUniqueCharacterInAString_387.firstUniqChar("aabbcdefgh"));
        System.out.println(firstUniqueCharacterInAString_387.firstUniqChar("aabbccddeefgfg"));
        System.out.println(firstUniqueCharacterInAString_387.firstUniqChar("leetcode"));
    }
}
