package com.xiaotian.demo.algorithm.leetcode;

import com.xiaotian.demo.algorithm.leetcode.util.BinaryTreeUtil;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class A_98_ValidateBinarySearchTreeTest {

    private static A_98_ValidateBinarySearchTree searchTree;

    @BeforeClass
    public static void init() {
        searchTree = new A_98_ValidateBinarySearchTree();
    }

    @Test
    public void isValidBSTOfNullTree() {
        assertTrue(searchTree.isValidBST(BinaryTreeUtil.generateNullTree()));
    }

    @Test
    public void isValidBSTOfOneNodeTree() {
        assertTrue(searchTree.isValidBST(BinaryTreeUtil.generateOneNodeTree()));
    }

    @Test
    public void isValidBSTOfComplexTree() {
        assertTrue(searchTree.isValidBST(BinaryTreeUtil.generateBinaryTree(new int[]{5})));
        assertTrue(searchTree.isValidBST(BinaryTreeUtil.generateBinaryTree(new int[]{8, 6, 10, 5, 7, 9, 11})));
        assertFalse(searchTree.isValidBST(BinaryTreeUtil.generateBinaryTree(new int[]{8, 1, 10, 5, 7, 9, 11})));
        assertTrue(searchTree.isValidBST(BinaryTreeUtil.generateBinaryTree(new int[]{8, -1, 10, -1, -1, 9, 11})));
        assertTrue(searchTree.isValidBST(BinaryTreeUtil.generateBinaryTree(new int[]{8, 6, 10, 5, 7, -1, 11})));
        assertFalse(searchTree.isValidBST(BinaryTreeUtil.generateBinaryTree(new int[]{8, 6, 10, 8, 7, -1, 11})));
        assertTrue(searchTree.isValidBST(BinaryTreeUtil.generateBinaryTree(new int[]{8, 6, 10, 5, 7, 9, -1})));
        assertFalse(searchTree.isValidBST(BinaryTreeUtil.generateBinaryTree(new int[]{10, 5, 15, -1, -1, 6, 20})));
    }
}