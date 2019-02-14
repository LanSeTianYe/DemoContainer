package com.xiaotian.demo.algorithm.leetcode;

import java.util.Arrays;

/**
 * <a href="https://leetcode.com/problems/minimum-cost-to-hire-k-workers/submissions/">Minimum Cost to Hire K Workers</a>
 */
public class MinimumCostToHireKWorkers_857 {

    public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
        double maxWage = 10000 * 10000;
        double result = (maxWage + 1) * quality.length;

        double[] actualWage = new double[quality.length];

        actualWage[0] = 0.0;
        int satisfyingNumber;
        for (int i = 0; i < quality.length; i++) {
            satisfyingNumber = 0;
            for (int j = 0; j < quality.length; j++) {
                double actualWageForJ = (quality[j] * wage[i] * 1.0) / (quality[i]);
                if (actualWageForJ < wage[j]) {
                    actualWage[j] = maxWage + 1;
                } else {
                    satisfyingNumber++;
                    actualWage[j] = actualWageForJ;
                }
            }
            if (satisfyingNumber >= K) {
                Arrays.sort(actualWage);
                double temp = 0;
                for (int n = 0; n < K; n++) {
                    temp = temp + actualWage[n];
                }
                result = Math.min(result, temp);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        MinimumCostToHireKWorkers_857 workers_857 = new MinimumCostToHireKWorkers_857();
        System.out.println(workers_857.mincostToHireWorkers(new int[]{10, 20, 5}, new int[]{70, 50, 30}, 2));
        System.out.println(workers_857.mincostToHireWorkers(new int[]{3, 1, 10, 10, 1}, new int[]{4, 8, 2, 2, 7}, 3));
    }
}
