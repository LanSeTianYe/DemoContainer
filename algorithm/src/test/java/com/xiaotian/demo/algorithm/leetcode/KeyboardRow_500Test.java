package com.xiaotian.demo.algorithm.leetcode;

import org.junit.Test;

import static org.junit.Assert.*;


public class KeyboardRow_500Test {

    private A_500_KeyboardRow keyboardRow_500 = new A_500_KeyboardRow();

    @Test
    public void test() {
        String[] words = new String[]{"wqeqweqweqw","dsadasdasd","dsadsadas","xzczxczxc"};
        assertTrue(keyboardRow_500.findWords(words).length == 4);
        words = new String[]{"asddqweqeqw","casdasd","weqweqw","xzxasdasdzxceqw","dsadczx","rewrwerwefsdf"};
        assertTrue(keyboardRow_500.findWords(words).length == 1);
    }

}