package com.xiaotian.demo.algorithm.leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class A_375_GuessNumberHigherOrLowerIITest {

    @Test
    public void getMoneyAmount() {
        A_375_GuessNumberHigherOrLowerII guessNumber = new A_375_GuessNumberHigherOrLowerII();

        int moneyAmount = guessNumber.getMoneyAmount(0);
        assertEquals(0, moneyAmount);

        moneyAmount = guessNumber.getMoneyAmount(1);
        assertEquals(0, moneyAmount);

        moneyAmount = guessNumber.getMoneyAmount(2);
        assertEquals(1, moneyAmount);

        moneyAmount = guessNumber.getMoneyAmount(10);
        assertEquals(16, moneyAmount);

        moneyAmount = guessNumber.getMoneyAmount(100);
        assertEquals(400, moneyAmount);

        moneyAmount = guessNumber.getMoneyAmount(200);
        assertEquals(952, moneyAmount);
    }
}


