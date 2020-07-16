package com.xiaotian.demo.algorithm.su;

/**
 * 短链生成器
 *
 * @author sunfeilong [2020/7/16 16:24]
 */
public class ShortUrlGenerator {

    private static final String[] elements = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
        "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
        "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

    private final RadixNumberAccumulator<String> radix;

    public ShortUrlGenerator() {
        radix = new RadixNumberAccumulator<>(elements);
    }

    public ShortUrlGenerator(long initNum) {
        radix = new RadixNumberAccumulator<>(elements, initNum);
    }

    public String next() {
        return radix.next();
    }
}
