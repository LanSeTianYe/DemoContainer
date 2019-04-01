package com.xiaotian.demo.algorithm.leetcode;

/**
 * <a href="https://leetcode.com/problems/escape-the-ghosts/">逃离 Ghosts</a>
 * 思路: 比较你(0,0) 和 target 以及 ghosts 和 target 之间的距离，只要ghosts的距离小于你的距离就可以追上你。
 */
public class A_789_EscapeTheGhosts {

    public boolean escapeGhosts(int[][] ghosts, int[] target) {
        if (null == ghosts || 0 == ghosts.length) {
            return true;
        }

        int needStep = Math.abs(target[0]) + Math.abs(target[1]);
        int ghostsMinStep = 0;
        for (int i = 0; i < ghosts.length; i++) {
            ghostsMinStep = Math.abs(ghosts[i][0] - target[0]) + Math.abs(ghosts[i][1] - target[1]);
            if (ghostsMinStep <= needStep) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        A_789_EscapeTheGhosts escapeTheGhosts = new A_789_EscapeTheGhosts();
        System.out.println(escapeTheGhosts.escapeGhosts(new int[][]{{1, 2}, {1, 3},}, new int[]{3, 3}));
        System.out.println(escapeTheGhosts.escapeGhosts(new int[][]{{-3, -3}, {-1, -1},}, new int[]{3, 3}));
    }
}
