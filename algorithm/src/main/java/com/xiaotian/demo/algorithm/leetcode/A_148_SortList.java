package com.xiaotian.demo.algorithm.leetcode;

import com.xiaotian.demo.algorithm.leetcode.common.ListNode;

public class A_148_SortList {

    public ListNode sortList(ListNode head) {
        if (null == head) {
            return null;
        }

        return sortList(head, getLength(head));
    }

    private int getLength(ListNode head) {
        int length = 0;
        ListNode temp = head;
        while (null != temp) {
            length++;
            temp = temp.next;
        }
        return length;
    }

    private ListNode sortList(ListNode head, int length) {
        if (length == 1) {
            head.next = null;
            return head;
        }

        int leftLength = length / 2;
        ListNode rightHead = sortList(cutAndGetHead(head, leftLength), length - leftLength);
        ListNode leftHead = sortList(head, leftLength);

        return merge(leftHead, rightHead);
    }

    private ListNode cutAndGetHead(ListNode head, int length) {
        ListNode rightHead;
        int index = 1;
        while (index < length) {
            head = head.next;
            index++;
        }
        rightHead = head.next;
        head.next = null;
        return rightHead;
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
