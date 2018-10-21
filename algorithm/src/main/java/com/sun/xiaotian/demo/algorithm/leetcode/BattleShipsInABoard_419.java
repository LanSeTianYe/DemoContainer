package com.sun.xiaotian.demo.algorithm.leetcode;

/**
 * <a href="https://leetcode.com/problems/battleships-in-a-board/"></a>
 * 思路： 由于输入都是合法的战舰数组，因此遍历数组当上一个和前一个都不是 'X' 的时候战舰的数量加一。
 */
public class BattleShipsInABoard_419 {

    public int countBattleships(char[][] board) {
        int result = 0;

        //first line
        boolean previousIsBattle = false;
        for (int i = 0; i < board[0].length; i++) {
            if (board[0][i] == 'X') {
                if (!previousIsBattle) {
                    result++;
                }
                previousIsBattle = true;
            } else {
                previousIsBattle = false;
            }
        }

        //other line
        for (int i = 1; i < board.length; i++) {
            previousIsBattle = false;
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 'X') {
                    if (!previousIsBattle && board[i - 1][j] == '.') {
                        result++;
                    }
                    previousIsBattle = true;
                } else {
                    previousIsBattle = false;
                }
            }
        }
        return result;
    }

    /**
     * 来源于别人提交的代码,可以参考用于处理第一行和第一列元素，思路很清晰
     *
     * @param board 战舰
     * @return
     */
    public int countBattleships_2(char[][] board) {
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 'X') {
                    boolean upIsEmpty = i == 0 || board[i - 1][j] == '.';
                    if (upIsEmpty) {
                        boolean leftIsEmpty = j == 0 || board[i][j - 1] == '.';
                        if (leftIsEmpty) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }


    public static void main(String[] args) {

        BattleShipsInABoard_419 battleShipsInABoard_419 = new BattleShipsInABoard_419();
        System.out.println(battleShipsInABoard_419.countBattleships(new char[][]{{'X', '.', '.', 'X'}, {'.', '.', '.', 'X'}, {'.', '.', '.', 'X'}}));
        System.out.println(battleShipsInABoard_419.countBattleships_2(new char[][]{{'X', '.', '.', 'X'}, {'.', '.', '.', 'X'}, {'.', '.', '.', 'X'}}));
        System.out.println(battleShipsInABoard_419.countBattleships(new char[][]{{'X', 'X', 'X', 'X'}}));
        System.out.println(battleShipsInABoard_419.countBattleships_2(new char[][]{{'X', 'X', 'X', 'X'}}));
        System.out.println(battleShipsInABoard_419.countBattleships(new char[][]{{'X', '.', 'X', '.', 'X'}}));
        System.out.println(battleShipsInABoard_419.countBattleships_2(new char[][]{{'X', '.', 'X', '.', 'X'}}));
        System.out.println(battleShipsInABoard_419.countBattleships(new char[][]{{'X', '.', 'X', '.', 'X'}, {'.', 'X', '.', 'X', '.'}}));
        System.out.println(battleShipsInABoard_419.countBattleships_2(new char[][]{{'X', '.', 'X', '.', 'X'}, {'.', 'X', '.', 'X', '.'}}));
    }
}
