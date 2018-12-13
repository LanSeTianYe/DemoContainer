package com.sun.xiaotian.demo.algorithm.leetcode;

/**
 * <a href="https://leetcode.com/problems/profitable-schemes/">盈利计划</a>
 * 有一个G人的犯罪团伙和一个他们的犯罪计划清单。
 * 第 i 个犯罪计划需要group[i]的人参加并且产生 profit[i] 的收益。
 * 一个罪犯只能参与一个犯罪计划。
 * 找到有多少种犯罪计划组合可以选择，要求至少产生P的收益，且每个组合的人数不能大于G。
 * 当组合数量非常大时返回 `10^9 + 7` 表示.
 * 说明：
 * Note:
 * <p>
 * 1 <= G <= 100
 * 0 <= P <= 100
 * 1 <= group[i] <= 100
 * 0 <= profit[i] <= 100
 * 1 <= group.length = profit.length <= 100
 * <p>
 * 例子1：
 * Input: G = 5, P = 3, group = [2,2], profit = [2,3]
 * Output: 2
 * Explanation:
 * To make a profit of at least 3, the gang could either commit crimes 0 and 1, or just crime 1.
 * In total, there are 2 schemes.
 * <p>
 * 例子2：
 * Input: G = 10, P = 5, group = [2,3,5], profit = [6,7,8]
 * Output: 7
 * Explanation:
 * To make a profit of at least 5, the gang could commit any crimes, as long as they commit one.
 * There are 7 possible schemes: (0), (1), (2), (0,1), (0,2), (1,2), and (0,1,2).
 */
public class ProfitableSchemes_879 {

    public int profitableSchemes(int G, int P, int[] group, int[] profit) {
        Result result = new Result();
        calculateProfit(G, P, group, profit, 0, 0, 0, result);
        return result.get();
    }


    public void calculateProfit(int G, int P, int[] group, int[] profit, int lastG, int lastP, int startIndex, Result result) {
        for (int i = startIndex; i < group.length; i++) {
            int newG1 = lastG + group[i];
            if (newG1 > G) {
                continue;
            }
            int newP1 = lastP + profit[i];
            if (newP1 >= P) {
                result.add();
            }
            calculateProfit(G, P, group, profit, newG1, newP1, i + 1, result);
        }
    }

    private class Result {
        private int number = 0;

        private void add() {
            number = number + 1;
        }

        private int get() {
            return number;
        }
    }

    public static void main(String[] args) {
        ProfitableSchemes_879 profitableSchemes_879 = new ProfitableSchemes_879();
        long start = System.currentTimeMillis();
        System.out.println(profitableSchemes_879.profitableSchemes(100, 10, new int[]{66, 43, 46, 24, 53, 49, 86, 37, 4, 70, 99, 68, 14, 91, 70, 71, 70, 98, 48, 26, 13, 86, 4, 82, 1, 7, 51, 37, 27, 87, 2, 65, 93, 66, 99, 28, 17, 93, 83, 91, 45, 3, 59, 87, 92, 62, 77, 21, 9, 37, 11, 4, 69, 46, 70, 47, 28, 40, 74, 47, 12, 3, 85, 16, 91, 100, 39, 24, 52, 50, 40, 23, 64, 22, 2, 15, 18, 62, 26, 76, 3, 60, 64, 34, 45, 40, 49, 11, 5, 8, 40, 71, 12, 60, 3, 51, 31, 5, 42, 52, 15, 36}, new int[]{8, 4, 8, 8, 9, 3, 1, 6, 7, 10, 1, 10, 4, 9, 7, 11, 5, 1, 7, 4, 11, 1, 5, 9, 9, 5, 1, 10, 0, 10, 4, 1, 1, 1, 6, 9, 3, 6, 2, 5, 4, 7, 8, 5, 2, 3, 0, 6, 4, 5, 9, 9, 10, 7, 1, 8, 9, 6, 0, 2, 9, 2, 2, 8, 6, 10, 3, 4, 6, 1, 10, 7, 5, 4, 8, 1, 8, 5, 5, 4, 1, 1, 10, 0, 8, 0, 1, 11, 5, 4, 7, 9, 1, 11, 1, 0, 1, 6, 8, 3, 3, 4}));
        System.out.println(System.currentTimeMillis() - start);
    }
}
