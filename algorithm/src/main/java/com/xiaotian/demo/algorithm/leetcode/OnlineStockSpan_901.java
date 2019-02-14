package com.xiaotian.demo.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.com/problems/online-stock-span/">Online Stock Span</a>
 */
public class OnlineStockSpan_901 {

    private final List<Integer> elementsList;

    public OnlineStockSpan_901() {
        elementsList = new ArrayList<>();
    }

    public int next(int price) {
        elementsList.add(price);
        for (int i = elementsList.size() - 1; i >= 0; i--) {
            if (elementsList.get(i) > price) {
                return (elementsList.size() - i) - 1;
            }
        }
        return elementsList.size();
    }
}
