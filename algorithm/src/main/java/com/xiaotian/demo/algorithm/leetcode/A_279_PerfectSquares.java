package com.xiaotian.demo.algorithm.leetcode;

import java.util.Arrays;
import java.util.LinkedList;

public class A_279_PerfectSquares {

    public int numSquares(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("param n must grater than 1.");
        }
        int[] squareTable = getSquareTable(n);
        LinkedList<Integer> resultQueue = new LinkedList<>();
        for (int square : squareTable) {
            if (square == n) {
                return 1;
            }
            resultQueue.addFirst(square);
        }

        int level = 1;
        int queueSize;
        while (true) {
            level++;
            queueSize = resultQueue.size();
            for (int i = 0; i < queueSize; i++) {
                Integer firstValue = resultQueue.removeFirst();
                for (int squareIndex = squareTable.length - 1; squareIndex >= 0; squareIndex--) {
                    int nextValue = firstValue + squareTable[squareIndex];
                    if (nextValue == n) {
                        return level;
                    } else if (nextValue < n) {
                        resultQueue.addLast(nextValue);
                    }
                }
            }
        }
    }

    private int[] getSquareTable(int maxNumber) {
        int[] squareTable = new int[maxNumber];
        int square = 1;
        int index = 1;
        while (square <= maxNumber) {
            squareTable[index - 1] = square;
            index++;
            square = square + index + index - 1;
        }
        return Arrays.copyOfRange(squareTable, 0, index - 1);
    }
}
