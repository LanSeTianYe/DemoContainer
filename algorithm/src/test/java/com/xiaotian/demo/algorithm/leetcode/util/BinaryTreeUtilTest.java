package com.xiaotian.demo.algorithm.leetcode.util;

import com.xiaotian.demo.algorithm.leetcode.common.TreeNode;
import org.junit.Test;

import static org.junit.Assert.*;

public class BinaryTreeUtilTest {

    @Test
    public void generateNullTree() {
        assertNull(BinaryTreeUtil.generateNullTree());
    }

    @Test
    public void generateOneNodeTree() {
        TreeNode treeNode = BinaryTreeUtil.generateOneNodeTree();
        assertNotNull(treeNode);
        assertNull(treeNode.left);
        assertNull(treeNode.right);
    }

    @Test
    public void generateBinarySearchTree() {
        TreeNode root = BinaryTreeUtil.generateBinaryTree(new int[]{8, 6, 10, -1, 7, 9, 11});
        assertEquals(root.val, 8);
        assertEquals(root.left.val, 6);
        assertEquals(root.right.val, 10);
        assertNull(root.left.left);
        assertEquals(root.left.right.val, 7);
        assertEquals(root.right.left.val, 9);
        assertEquals(root.right.right.val, 11);
    }
}