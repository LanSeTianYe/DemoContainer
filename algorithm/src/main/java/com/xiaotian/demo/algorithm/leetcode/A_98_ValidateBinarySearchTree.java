package com.xiaotian.demo.algorithm.leetcode;

import com.xiaotian.demo.algorithm.leetcode.common.TreeNode;

public class A_98_ValidateBinarySearchTree {

    public boolean isValidBST(TreeNode root) {
        return isValidBst(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidBst(TreeNode root, long min, long max) {
        if (null == root) {
            return true;
        }

        if (root.val <= min || root.val >= max) {
            return false;
        }

        boolean leftIsValid = true;
        boolean rightIsValid = true;

        if (null != root.left) {
            leftIsValid = isValidBst(root.left, min, Math.min(max, root.val));
        }

        if (null != root.right) {
            rightIsValid = isValidBst(root.right, Math.max(min, root.val), max);
        }

        return leftIsValid && rightIsValid;
    }
}
