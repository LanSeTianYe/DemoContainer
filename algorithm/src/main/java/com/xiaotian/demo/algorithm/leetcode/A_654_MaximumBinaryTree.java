package com.xiaotian.demo.algorithm.leetcode;

import com.xiaotian.demo.algorithm.leetcode.common.TreeNode;


public class A_654_MaximumBinaryTree {

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums == null) {
            return null;
        }
        return buildTree(nums, 0, nums.length);
    }

    private TreeNode buildTree(int[] nums, int start, int end) {
        if (start >= end) {
            return null;
        }

        if (start == end - 1) {
            return new TreeNode(nums[start]);
        }

        TreeNode treeNode = new TreeNode();
        int maxIndex = maxIndex(nums, start, end);
        treeNode.val = nums[maxIndex];
        treeNode.left = buildTree(nums, start, maxIndex);
        treeNode.right = buildTree(nums, maxIndex + 1, end);
        return treeNode;
    }

    private int maxIndex(int[] nums, int start, int end) {
        int maxIndex = start;
        for (int i = start + 1; i < end; i++) {
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }
}
