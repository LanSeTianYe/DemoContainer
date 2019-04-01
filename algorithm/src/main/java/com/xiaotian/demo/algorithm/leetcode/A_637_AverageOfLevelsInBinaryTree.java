package com.xiaotian.demo.algorithm.leetcode;

import java.util.*;

/**
 * https://leetcode.com/problems/average-of-levels-in-binary-tree/
 */
public class A_637_AverageOfLevelsInBinaryTree {

    public List<Double> averageOfLevels(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        List<Double> result = new ArrayList<>();

        Queue<TreeNode> levelTreeNodes = new LinkedList<>();
        levelTreeNodes.add(root);

        int levelNumber = 1;

        while (levelNumber != 0) {
            result.add(averageOfLevel(levelNumber, levelTreeNodes));
            levelNumber = levelTreeNodes.size();
        }

        return result;
    }

    private double averageOfLevel(int levelNodeCount, Queue<TreeNode> nodes) {
        long sum = 0;
        for (int i = 0; i < levelNodeCount; i++) {
            TreeNode node = nodes.poll();
            sum = sum + node.val;
            if (node.left != null) {
                nodes.offer(node.left);
            }
            if (node.right != null) {
                nodes.offer(node.right);
            }
        }
        return sum * 1.0 / levelNodeCount;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}


