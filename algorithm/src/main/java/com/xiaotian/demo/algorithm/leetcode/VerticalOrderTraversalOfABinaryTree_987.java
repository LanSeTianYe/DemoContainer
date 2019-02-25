package com.xiaotian.demo.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * <a href="https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/">Vertical Order Traversal of a Binary Tree</a>
 * 坐标实现Comparable接口，遍历所有节点排序，然后一次取出元素
 */
public class VerticalOrderTraversalOfABinaryTree_987 {

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        List<NodeIndex> nodeIndexList = new ArrayList<>();
        traversalTree(root, 0, 0, nodeIndexList);
        Collections.sort(nodeIndexList);

        NodeIndex last = null;
        List<Integer> groupNode = new ArrayList<>();
        for (NodeIndex nodeIndex : nodeIndexList) {
            if (last == null) {
                groupNode = new ArrayList<>();
                last = nodeIndex;
                groupNode.add(nodeIndex.value);
            } else if (last.x != nodeIndex.x) {
                result.add(groupNode);
                groupNode = new ArrayList<>();
                last = nodeIndex;
                groupNode.add(nodeIndex.value);
            } else {
                groupNode.add(nodeIndex.value);
            }
        }
        if (groupNode.size() > 0) {
            result.add(groupNode);
        }
        return result;
    }

    private void traversalTree(TreeNode node, int x, int y, List<NodeIndex> result) {
        if (null != node) {
            NodeIndex nodeIndex = new NodeIndex(x, y, node.val);
            result.add(nodeIndex);
            traversalTree(node.left, x - 1, y - 1, result);
            traversalTree(node.right, x + 1, y - 1, result);
        }
    }

    class NodeIndex implements Comparable<NodeIndex> {
        final int x;
        final int y;
        final int value;

        NodeIndex(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }

        @Override
        public int compareTo(NodeIndex anotherNode) {
            if (this.x != anotherNode.x) {
                return this.x - anotherNode.x;
            }
            if (this.y != anotherNode.y) {
                return anotherNode.y - this.y;
            }
            return this.value - anotherNode.value;
        }
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
