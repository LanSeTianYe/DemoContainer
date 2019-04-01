package com.xiaotian.demo.algorithm.leetcode;

import java.util.Arrays;
import java.util.Random;

public class A_528_RandomPickWithWeight {

    private Random random = new Random(37);
    private final int[] weightsRange;
    private int sumOfWeights;

    public A_528_RandomPickWithWeight(int[] w) {
        this.sumOfWeights = Arrays.stream(w).sum();
        this.weightsRange = new int[w.length];
        for (int i = 0; i < w.length; i++) {
            this.weightsRange[i] = (i == 0 ? w[i] : w[i] + this.weightsRange[i - 1]);
        }
    }

    public int pickIndex() {
        int nextInt = random.nextInt(this.sumOfWeights) + 1;
        for (int i = 0; i < this.weightsRange.length; i++) {
            if (this.weightsRange[i] >= nextInt) {
                return i;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        A_528_RandomPickWithWeight randomPickWithWeight = new A_528_RandomPickWithWeight(new int[]{3, 14, 1, 7});
        for (int i = 0; i < 1000; i++) {
            System.out.println(randomPickWithWeight.pickIndex());
        }
    }
}
