package com.xiaotian.demo.algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.Queue;

public class A_97_Interleaving_String {

    public boolean isInterleave(String s1, String s2, String s3) {
        int s1AndS2Length = getLength(s1) + getLength(s2);
        int s3Length = getLength(s3);
        if (s1AndS2Length != s3Length) {
            return false;
        }
        if (s3Length == 0) {
            return true;
        }

        Queue<Character> s1Queue = new ArrayDeque<>();
        Queue<Character> s2Queue = new ArrayDeque<>();
        Queue<Character> s3Queue = new ArrayDeque<>();
        for (char c : s1.toCharArray()) {
            s1Queue.add(c);
        }
        for (char c : s2.toCharArray()) {
            s2Queue.add(c);
        }
        for (char c : s3.toCharArray()) {
            s3Queue.add(c);
        }

        while (!s3Queue.isEmpty()) {
            Character c = s3Queue.poll();
            if (c.equals(s1Queue.peek())) {
                s1Queue.poll();

            } else if (c.equals(s2Queue.peek())) {
                s2Queue.poll();
            } else {
                return false;
            }
        }
        return true;
    }


    private int getLength(String str) {
        return null == str ? 0 : str.length();
    }
}
