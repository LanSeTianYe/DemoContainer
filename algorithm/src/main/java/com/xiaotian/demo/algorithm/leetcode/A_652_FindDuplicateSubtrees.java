package com.xiaotian.demo.algorithm.leetcode;

import com.xiaotian.demo.algorithm.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class A_652_FindDuplicateSubtrees {

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        Map<String, Integer> count = new HashMap<>();
        List<TreeNode> result = new ArrayList<>();
        visitTree(root, count, result);
        return result;
    }

    private String visitTree(TreeNode root, Map<String, Integer> count, List<TreeNode> result) {
        if (root == null) {
            return "N";
        }
        String left = visitTree(root.left, count, result);
        String right = visitTree(root.right, count, result);
        String s = root.val + "," + left + "," + right;

        int countForS = count.getOrDefault(s, 0) + 1;
        count.put(s, countForS);
        if (countForS == 2) {
            result.add(root);
        }
        return s;
    }
}
