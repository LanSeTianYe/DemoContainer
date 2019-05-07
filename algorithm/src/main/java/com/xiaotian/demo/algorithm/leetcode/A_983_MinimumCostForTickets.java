package com.xiaotian.demo.algorithm.leetcode;

public class A_983_MinimumCostForTickets {

    public int mincostTickets(int[] days, int[] costs) {
        if (null == days || days.length == 0 || costs == null || costs.length != 3) {
            throw new IllegalArgumentException();
        }

        int[] minCosts = new int[366];
        boolean[] isDay = new boolean[366];

        int oneDayCost = costs[0];
        int sevenDayCost = costs[1];
        int thirtyDayCost = costs[2];

        for (int day : days) isDay[day] = true;

        for (int i = 1; i < minCosts.length; i++) {
            if (!isDay[i]) {
                minCosts[i] = minCosts[i - 1];
                continue;
            }
            int min = 0;
            min = minCosts[i - 1] + oneDayCost;
            min = Math.min(min, sevenDayCost + minCosts[i - 7 > 0 ? i - 7 : 0]);
            min = Math.min(min, thirtyDayCost + minCosts[i - 30 > 0 ? i - 30 : 0]);
            minCosts[i] = min;
        }
        return minCosts[minCosts.length - 1];
    }
}
