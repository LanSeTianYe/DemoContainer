package com.xiaotian.demo.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.com/problems/stone-game/solution/">Stone Game</a>
 * 思路1：递归枚举所有可能
 * 思路2：第一个拿的一定会赢，直接 return true
 * 假设有 2 个，可以控制拿最后两个中的哪一个，因此总能拿到最大的。
 * 假设有 4 个，可以控制拿最后两个中的哪一个，总能拿到第一个和最后两个中一个的最大组合，因此总难拿到四个中最大的组合。
 * 假设有 6 个，可以拿到四个中最大的组合，因此结合第一个总能拿到和最大的三个书。
 * 因此 第一个拿的一定会赢。
 */
public class A_877_StoneGame {

    private final Map<String, Integer> sumCache = new HashMap<>();

    public boolean stoneGame(int[] piles) {
        sumCache.clear();
        int sum = 0;
        for (int i = 0; i < piles.length; i++) {
            sum = sum + piles[i];
        }

        return sum(piles, 0, piles.length - 1) * 2 > sum;
    }

    private int sum(int[] piles, int start, int end) {
        if (start + 1 == end) {
            return Math.max(piles[start], piles[end]);
        }
        String key = start + "_" + end;
        if (null != sumCache.get(key)) {
            return sumCache.get(key);
        }
        int fetchFirst = piles[start] + Math.max(sum(piles, start + 2, end), sum(piles, start + 1, end - 1));
        int fetchLast = piles[end] + Math.max(sum(piles, start + 1, end - 1), sum(piles, start, end - 2));
        int value = Math.max(fetchFirst, fetchLast);
        sumCache.put(key, value);
        return value;
    }

    public static void main(String[] args) {
        A_877_StoneGame stoneGame_877 = new A_877_StoneGame();
        System.out.println(stoneGame_877.stoneGame(new int[]{1, 2}));
        System.out.println(stoneGame_877.stoneGame(new int[]{5, 3, 4, 5}));
        System.out.println(stoneGame_877.stoneGame(new int[]{4, 8, 5, 4}));
        System.out.println(stoneGame_877.stoneGame(new int[]{7, 8, 5, 3}));
        System.out.println(stoneGame_877.stoneGame(new int[]{1, 8, 9, 10, 7, 1}));
        System.out.println(stoneGame_877.stoneGame(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8}));
        System.out.println(stoneGame_877.stoneGame(new int[]{7, 7, 12, 16, 41, 48, 41, 48, 11, 9, 34, 2, 44, 30, 27, 12, 11, 39, 31, 8, 23, 11, 47, 25, 15, 23, 4, 17, 11, 50, 16, 50, 38, 34, 48, 27, 16, 24, 22, 48, 50, 10, 26, 27, 9, 43, 13, 42, 46, 24}));
    }
}
