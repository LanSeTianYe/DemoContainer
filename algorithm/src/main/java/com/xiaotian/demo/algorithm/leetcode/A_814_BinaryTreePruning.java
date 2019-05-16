package com.xiaotian.demo.algorithm.leetcode;

import com.xiaotian.demo.algorithm.leetcode.common.TreeNode;

public class A_814_BinaryTreePruning {

    public TreeNode pruneTree(TreeNode root) {
        return containsOne(root) ? root : null;
    }

    private boolean containsOne(TreeNode root) {
        if (root == null) {
            return false;
        }

        boolean leftContains = containsOne(root.left);
        boolean rightContains = containsOne(root.right);

        if (!leftContains) {
            root.left = null;
        }
        if (!rightContains) {
            root.right = null;
        }
        return leftContains || rightContains || root.val == 1;
    }
}
