package com.xiaotian.demo.algorithm.leetcode;

public class A_375_GuessNumberHigherOrLowerII {

    public int getMoneyAmount(int n) {
        if (n <= 1) {
            return 0;
        }

        int left = 1;
        int cost = 0;

        while (true) {
            int center = getCenter(left, n);
            if (this.isEnd(center, n)) {
                break;
            }
            cost = cost + center;
            left = center + 1;
        }

        return cost;
    }

    public int getCenter(int left, int right) {
        return (left + right) / 2;
    }

    public boolean isEnd(int left, int right) {
        return left == right;
    }
}
