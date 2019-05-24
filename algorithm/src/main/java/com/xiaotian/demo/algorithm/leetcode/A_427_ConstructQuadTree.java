package com.xiaotian.demo.algorithm.leetcode;

import com.xiaotian.demo.algorithm.leetcode.common.Node;

public class A_427_ConstructQuadTree {

    public Node construct(int[][] grid) {

        if (null == grid || grid.length == 0) {
            return new Node();
        }

        return construct(grid, grid.length, 0, 0);
    }

    private Node construct(int[][] grid, int length, int startRow, int startCol) {
        if (length == 1 || isLeaf(grid, length, startRow, startCol)) {
            return new Node(
                    grid[startRow][startCol] == 1,
                    true,
                    null,
                    null,
                    null,
                    null);
        } else {
            int subLength = length / 2;
            return new Node(
                    grid[startRow][startCol] == 1,
                    false,
                    construct(grid, subLength, startRow, startCol),
                    construct(grid, subLength, startRow, startCol + subLength),
                    construct(grid, subLength, startRow + subLength, startCol),
                    construct(grid, subLength, startRow + subLength, startCol + subLength)
            );
        }
    }

    private boolean isLeaf(int[][] grid, int length, int startRow, int startCol) {
        int value = grid[startRow][startCol];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (grid[startRow + i][startCol + j] != value) {
                    return false;
                }
            }
        }
        return true;
    }
}
