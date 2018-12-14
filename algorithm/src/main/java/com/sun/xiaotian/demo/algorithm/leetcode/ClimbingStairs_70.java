package com.sun.xiaotian.demo.algorithm.leetcode;

/**
 * <a href="https://leetcode.com/submissions/detail/195039447/">上楼梯问题</a>
 */
public class ClimbingStairs_70 {

    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int[] sumArr = new int[n + 1];
        sumArr[1] = 1;
        sumArr[2] = 2;
        for (int i = 3; i <= n; i++) {
            sumArr[i] = sumArr[i - 1] + sumArr[i - 2];
        }
        return sumArr[n];
    }

    public static void main(String[] args) {

        ClimbingStairs_70 climbingStairs_70 = new ClimbingStairs_70();
        System.out.println(climbingStairs_70.climbStairs(1));
        System.out.println(climbingStairs_70.climbStairs(10));
        System.out.println(climbingStairs_70.climbStairs(100));

    }
}
