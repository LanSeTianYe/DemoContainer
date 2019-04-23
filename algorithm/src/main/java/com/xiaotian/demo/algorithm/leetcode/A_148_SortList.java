package com.xiaotian.demo.algorithm.leetcode;

import com.xiaotian.demo.algorithm.leetcode.common.ListNode;

public class A_148_SortList {

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode rightHead = sortList(cutAndGetRightHead(head));
        ListNode leftHead = sortList(head);

        return merge(leftHead, rightHead);
    }

    private ListNode cutAndGetRightHead(ListNode head) {
        ListNode newHead;
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast.next != null && null != fast.next.next) {
            fast = fast.next.next;
            slow = slow.next;
        }
        newHead = slow.next;
        slow.next = null;
        return newHead;
    }

    private ListNode merge(ListNode leftHead, ListNode rightHead) {
        ListNode head = new ListNode(-1);
        ListNode currNode = head;

        while (null != leftHead && rightHead != null) {
            if (leftHead.val < rightHead.val) {
                currNode.next = leftHead;
                leftHead = leftHead.next;
            } else {
                currNode.next = rightHead;
                rightHead = rightHead.next;
            }
            currNode = currNode.next;
        }

        if (null != leftHead) {
            currNode.next = leftHead;
        }
        if (null != rightHead) {
            currNode.next = rightHead;
        }
        return head.next;
    }
}
