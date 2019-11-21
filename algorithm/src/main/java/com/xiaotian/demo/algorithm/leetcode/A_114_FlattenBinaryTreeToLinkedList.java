package com.xiaotian.demo.algorithm.leetcode;

import com.xiaotian.demo.algorithm.leetcode.common.TreeNode;

public class A_114_FlattenBinaryTreeToLinkedList {

    public void flatten(TreeNode root) {
        if (root != null) {
            flatten(root.left);
            flatten(root.right);
            if (null != root.left) {
                if (root.right == null) {
                    root.right = root.left;
                } else {
                    TreeNode right = root.right;
                    root.right = root.left;
                    TreeNode lastRight = getLastRight(root.left);
                    lastRight.right = right;
                }
                root.left = null;
            }
        }
    }

    private TreeNode getLastRight(TreeNode node) {
        TreeNode result = node;
        while (result.right != null) {
            result = result.right;
        }
        return result;
    }
}
