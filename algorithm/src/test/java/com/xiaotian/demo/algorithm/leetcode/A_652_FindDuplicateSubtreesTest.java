package com.xiaotian.demo.algorithm.leetcode;

import com.xiaotian.demo.algorithm.leetcode.common.TreeNode;
import com.xiaotian.demo.algorithm.leetcode.util.BinaryTreeUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class A_652_FindDuplicateSubtreesTest {

    private A_652_FindDuplicateSubtrees a_652_findDuplicateSubtrees;

    @Before
    public void setUp() throws Exception {
        a_652_findDuplicateSubtrees = new A_652_FindDuplicateSubtrees();
    }

    @Test
    public void findDuplicateSubtrees() {
        List<TreeNode> result;
        result = a_652_findDuplicateSubtrees.findDuplicateSubtrees(null);
        assertEquals(0, result.size());

        result = a_652_findDuplicateSubtrees.findDuplicateSubtrees(BinaryTreeUtil.generateBinaryTree(new int[]{1, 2, 3, 4, -1, 2, 4, -1, -1, -1, -1, 4}));
        assertEquals(2, result.size());
    }
}