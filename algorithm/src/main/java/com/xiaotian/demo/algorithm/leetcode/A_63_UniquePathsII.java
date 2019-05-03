package com.xiaotian.demo.algorithm.leetcode;

public class A_63_UniquePathsII {

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (null == obstacleGrid || obstacleGrid.length == 0 ||
                obstacleGrid[0] == null || obstacleGrid[0].length == 0) {
            throw new IllegalArgumentException();
        }

        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        if (obstacleGrid[0][0] == 1) {
            return 0;
        } else {
            obstacleGrid[0][0] = -1;
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    obstacleGrid[i][j] = 0;
                } else if ((obstacleGrid[i][j] == 0)) {
                    int leftPaths = j == 0 ? 0 : obstacleGrid[i][j - 1];
                    int topPaths = i == 0 ? 0 : obstacleGrid[i - 1][j];
                    obstacleGrid[i][j] = leftPaths + topPaths;
                }
            }
        }
        return -obstacleGrid[m - 1][n - 1];
    }
}
