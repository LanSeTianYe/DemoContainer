package com.xiaotian.demo.algorithm.leetcode.util;

import com.xiaotian.demo.algorithm.leetcode.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTreeUtil {

    public static TreeNode generateNullTree() {
        return null;
    }

    public static TreeNode generateOneNodeTree() {
        return new TreeNode(10);
    }

    /**
     * 根据数组生成对应的二叉树，元素值小于0代表节点为空
     *
     * @param nodes 节点
     * @return 二叉树
     */
    public static TreeNode generateBinaryTree(int[] nodes) {
        if (nodes.length < 1) {
            throw new IllegalArgumentException("nodes length mast grater than 1");
        }

        TreeNode root = new TreeNode(nodes[0]);
        Queue<TreeNode> parentQueue = new LinkedList<>();
        parentQueue.add(root);
        int index = 1;
        while (index < nodes.length) {
            TreeNode parent = parentQueue.poll();
            if (null == parent) {
                parentQueue.add(null);
                parentQueue.add(null);
                index = index + 2;
            } else {
                TreeNode leftNode = generateNode(nodes[index]);
                parent.left = leftNode;
                parentQueue.add(leftNode);
                index++;
                if (index >= nodes.length) {
                    break;
                }

                TreeNode rightNode = generateNode(nodes[index]);
                parent.right = rightNode;
                parentQueue.add(rightNode);
                index++;
                if (index >= nodes.length) {
                    break;
                }
            }
        }
        return root;
    }

    private static TreeNode generateNode(int val) {
        if (val < 0) {
            return null;
        }

        return new TreeNode(val);
    }
}
