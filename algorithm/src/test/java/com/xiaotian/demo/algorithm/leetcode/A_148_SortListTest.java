package com.xiaotian.demo.algorithm.leetcode;

import com.xiaotian.demo.algorithm.leetcode.common.ListNode;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class A_148_SortListTest {

    private static A_148_SortList sortList;

    @BeforeClass
    public static void init() {
        sortList = new A_148_SortList();
    }

    @Test
    public void sortList() {
        assertNull(sortList.sortList(null));

        ListNode node1 = new ListNode(1);
        assertEquals(1, sortList.sortList(node1).val);
        ListNode node2 = new ListNode(2);
        node1.next = node2;
        ListNode node3 = new ListNode(2);
        node2.next = node3;
        ListNode node4 = new ListNode(8);
        node3.next = node4;
        ListNode node5 = new ListNode(4);
        node4.next = node5;
        ListNode node6 = new ListNode(6);
        node5.next = node6;
        ListNode node7 = new ListNode(7);
        node6.next = node7;

        ListNode head = sortList.sortList(node1);
        assertTrue(isOrder(head));
    }

    private boolean isOrder(ListNode head) {
        long last = Long.MIN_VALUE;
        while (head != null) {
            if (head.val < last) {
                return false;
            }
            last = head.val;
            head = head.next;
        }
        return true;
    }
}