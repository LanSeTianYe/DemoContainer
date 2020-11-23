package com.xiaotian.demo.algorithm.leetcode;

import com.xiaotian.demo.algorithm.leetcode.common.TreeNode;
import org.junit.Assert;
import org.junit.Test;

public class A_654_MaximumBinaryTreeTest {

    @Test
    public void constructMaximumBinaryTree() {
        A_654_MaximumBinaryTree a_654_maximumBinaryTree = new A_654_MaximumBinaryTree();
        TreeNode treeNode = a_654_maximumBinaryTree.constructMaximumBinaryTree(new int[]{3, 2, 1, 6, 0, 5});
        Assert.assertNotNull(treeNode);
    }
}
