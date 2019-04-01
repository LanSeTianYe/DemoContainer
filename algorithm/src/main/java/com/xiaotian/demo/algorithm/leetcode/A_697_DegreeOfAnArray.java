package com.xiaotian.demo.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.com/problems/degree-of-an-array/">degree-of-an-array</a>
 */
public class A_697_DegreeOfAnArray {

    public int findShortestSubArray(int[] nums) {
        Map<Integer, Node> numberAndInfo = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Node node = numberAndInfo.get(nums[i]);
            if (node == null) {
                node = Node.init(i);
                numberAndInfo.put(nums[i], node);
            } else {
                node.fresh(i);
            }
        }
        int times = 0;
        int length = Integer.MAX_VALUE;
        for (Map.Entry<Integer, Node> integerNodeEntry : numberAndInfo.entrySet()) {
            if (times == integerNodeEntry.getValue().getTimes()) {
                length = Math.min(integerNodeEntry.getValue().getLength(), length);
            }
            if (times < integerNodeEntry.getValue().getTimes()) {
                times = integerNodeEntry.getValue().getTimes();
                length = integerNodeEntry.getValue().getLength();
            }
        }
        return length;
    }

    private static class Node {
        private int times;
        private int start;
        private int end;
        private int length;

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public int getEnd() {
            return end;
        }

        public void setEnd(int end) {
            this.end = end;
        }

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }

        public int getTimes() {
            return times;
        }

        public void setTimes(int times) {
            this.times = times;
        }

        public void fresh(int index) {
            this.setEnd(index);
            this.setLength(index - start + 1);
            this.setTimes(this.times + 1);
        }

        public static Node init(int index) {
            Node node = new Node();
            node.setStart(index);
            node.setEnd(index);
            node.setLength(1);
            node.setTimes(1);
            return node;
        }
    }

    public static void main(String[] args) {
        A_697_DegreeOfAnArray degreeOfAnArray_697 = new A_697_DegreeOfAnArray();
        System.out.println(degreeOfAnArray_697.findShortestSubArray(new int[]{1, 2, 2, 3, 1, 4, 2}));
    }
}
