package com.xiaotian.demo.algorithm.leetcode;

import com.xiaotian.demo.algorithm.leetcode.common.ListNode;

/**
 * <a href="https://leetcode.com/problems/rotate-list/"></>
 * 思路：
 * 1.先计算列表的总长度，根据指定的k计算出新的首节点的位置。
 * 2.把原有的列表构造成一个环。
 * 3.根据新的首节点位置把环断开。
 */
public class A_61_RotateList {

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        int headIndex = calculateHeadIndex(head, k);
        if (headIndex == 0) {
            return head;
        }
        ListNode tail = makeListAsACircle(head);
        for (int i = 0; i < headIndex; i++) {
            head = tail.next.next;
            tail = tail.next;
        }
        tail.next = null;
        return head;
    }


    private ListNode makeListAsACircle(ListNode head) {
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = head;
        return tail;
    }


    private int calculateHeadIndex(ListNode head, int k) {
        ListNode currNode = head;
        int listLength = 1;
        while (currNode.next != null) {
            listLength++;
            currNode = currNode.next;
        }
        return k >= listLength ? listLength - (k % listLength) : listLength - k;
    }


    public static void main(String[] args) {
        A_61_RotateList rotateList_61 = new A_61_RotateList();

        //test1
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        ListNode listNode6 = new ListNode(6);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = listNode6;

        ListNode listNode = rotateList_61.rotateRight(listNode1, 4);
        rotateList_61.showListNode(listNode);

    }


    private void showListNode(ListNode head) {
        ListNode tempHead = head;
        if (tempHead != null) {
            System.out.println(tempHead.val);
            while (tempHead.next != null) {
                tempHead = tempHead.next;
                System.out.println("" + tempHead.val);
            }

        }
    }
}
