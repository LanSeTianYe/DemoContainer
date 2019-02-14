package com.xiaotian.demo.algorithm.leetcode;

public class GuessNumberHigherOrLower_374 {

    private final static int pick = 1702766719;

    public static void main(String[] args) {
        int number = 2126753390;
        GuessNumberHigherOrLower_374 guess = new GuessNumberHigherOrLower_374();
        System.out.println(guess.guessNumber(number));
    }

    public int guessNumber(int n) {

        long start = 1;
        long end = n;
        int value = 0;
        int result = 0;

        while (true) {
            value = (int) ((start + end) / 2);
            result = guess(value);
            if (result == 0) {
                return value;
            } else if (result == -1) {
                end = value - 1;
            } else {
                start = value + 1;
            }
        }
    }

    private int guess(int value) {
        return Integer.compare(pick, value);
    }
}
