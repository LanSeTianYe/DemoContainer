package com.xiaotian.demo.algorithm.leetcode;


import java.util.ArrayList;
import java.util.List;

public class KeyboardRow_500 {

    private static boolean[] lineOne = new boolean[127];
    private static boolean[] lineTwo = new boolean[127];
    private static boolean[] lineThree = new boolean[127];

    static {
        lineOne['q'] = true;
        lineOne['w'] = true;
        lineOne['e'] = true;
        lineOne['r'] = true;
        lineOne['t'] = true;
        lineOne['y'] = true;
        lineOne['u'] = true;
        lineOne['i'] = true;
        lineOne['o'] = true;
        lineOne['p'] = true;
        lineOne['Q'] = true;
        lineOne['W'] = true;
        lineOne['E'] = true;
        lineOne['R'] = true;
        lineOne['T'] = true;
        lineOne['Y'] = true;
        lineOne['U'] = true;
        lineOne['I'] = true;
        lineOne['O'] = true;
        lineOne['P'] = true;

        lineTwo['a'] = true;
        lineTwo['s'] = true;
        lineTwo['d'] = true;
        lineTwo['f'] = true;
        lineTwo['g'] = true;
        lineTwo['h'] = true;
        lineTwo['j'] = true;
        lineTwo['k'] = true;
        lineTwo['l'] = true;
        lineTwo['A'] = true;
        lineTwo['S'] = true;
        lineTwo['D'] = true;
        lineTwo['F'] = true;
        lineTwo['G'] = true;
        lineTwo['H'] = true;
        lineTwo['J'] = true;
        lineTwo['K'] = true;
        lineTwo['L'] = true;

        lineThree['z'] = true;
        lineThree['x'] = true;
        lineThree['c'] = true;
        lineThree['v'] = true;
        lineThree['b'] = true;
        lineThree['n'] = true;
        lineThree['m'] = true;
        lineThree['Z'] = true;
        lineThree['X'] = true;
        lineThree['C'] = true;
        lineThree['V'] = true;
        lineThree['B'] = true;
        lineThree['N'] = true;
        lineThree['M'] = true;
    }

    public String[] findWords(String[] words) {
        if(null == words || words.length == 0) {
            return new String[]{};
        }
        List<String> result = new ArrayList<>();
        for (String word : words) {
            if(wordIsBelongOneLine(word)) {
                result.add(word);
            }
        }
        return result.toArray(new String[result.size()]);
    }

    private boolean wordIsBelongOneLine(String word) {
        if(null == word || word.length() == 0) {
            return false;
        }

        boolean[] line = getLine(word.charAt(0));
        boolean onSameLine = true;
        for (int i = 1; i < word.length(); i++) {
            if(!line[word.charAt(i)]) {
                onSameLine = false;
                break;
            }
        }
        return onSameLine;
    }

    private boolean[] getLine(char alphabet) {
        if(lineOne[alphabet]) {
            return lineOne;
        }
        if(lineTwo[alphabet]) {
            return lineTwo;
        }
        return lineThree;
    }
}
