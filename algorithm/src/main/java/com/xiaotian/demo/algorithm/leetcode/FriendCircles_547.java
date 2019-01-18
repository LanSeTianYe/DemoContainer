package com.xiaotian.demo.algorithm.leetcode;

/**
 * <a href="https://leetcode.com/problems/friend-circles/">547. Friend Circles</a>
 * 存在递推关系， a->b b->c c->d => a->d
 * 找出第一个圈子内的所有人，在从不在已经存在的圈子里的人查找。
 */
public class FriendCircles_547 {

    public int findCircleNum(int[][] M) {
        int totalCircles = 0;
        int[] inCircles = new int[M.length];
        for (int i = 0; i < M.length; i++) {
            if (1 != inCircles[i]) {
                finFriend(inCircles, i, M);
                totalCircles++;
            }
        }
        return totalCircles;
    }

    private void finFriend(int[] inCircles, int student, int[][] M) {
        int[] friends = M[student];
        for (int j = 0; j < friends.length; j++) {
            if (1 != inCircles[j] && 1 == friends[j]) {
                inCircles[j] = 1;
                finFriend(inCircles, j, M);
            }
        }
    }

    public static void main(String[] args) {
        FriendCircles_547 friendCircles_547 = new FriendCircles_547();

        System.out.println(friendCircles_547.findCircleNum(new int[][]{
                {1, 1, 0},
                {1, 1, 1},
                {0, 1, 1}
        }));
        System.out.println(friendCircles_547.findCircleNum(new int[][]{
                {1, 1, 0},
                {1, 1, 0},
                {0, 0, 1}
        }));
        System.out.println(friendCircles_547.findCircleNum(new int[][]{
                {1, 1, 0, 0, 0, 0},
                {1, 1, 1, 0, 0, 0},
                {0, 1, 1, 1, 0, 0},
                {0, 0, 1, 1, 1, 0},
                {0, 0, 0, 1, 1, 1},
                {0, 0, 0, 0, 1, 1}
        }));
        System.out.println(friendCircles_547.findCircleNum(new int[][]{
                {1, 0, 0, 1},
                {0, 1, 1, 0},
                {0, 1, 1, 1},
                {1, 0, 1, 1}
        }));
    }
}
