package com.xiaotian.demo.algorithm.leetcode;

/**
 * <a href="https://leetcode.com/problems/2-keys-keyboard/">2 Keys Keyboard</a>
 * 思路：
 * 100
 * <p>
 * 100 50 25 55555 11111
 */
public class A_650_2KeysKeyboard {

    public int minSteps(int n) {
        if (n == 1) {
            return 0;
        }

        int step = 0;
        int last = n;
        int next = last;

        while (true) {
            last = next;
            next = nextStepNumber(last);
            if (last == next) {
                step = step + 1;
                step = step + (last - 1);
                break;
            } else {
                step = step + 1;
                step = step + ((last / next) - 1);
            }
        }
        return step;
    }

    private int nextStepNumber(int n) {
        int next = n;
        for (int i = 2; i <= n / 2; i++) {
            if (n % i == 0) {
                next = n / i;
                break;
            }
        }
        return next;
    }

    public static void main(String[] args) {
        A_650_2KeysKeyboard keyboard = new A_650_2KeysKeyboard();
        System.out.println(keyboard.minSteps(1000));
        System.out.println(keyboard.minSteps(100));
        System.out.println(keyboard.minSteps(3));
    }
}
