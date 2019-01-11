package com.xiaotian.demo.algorithm.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LRUCache_UseLinkedList_146 {

    private final int capacity;

    private final LinkedList<Node> caches = new LinkedList<>();

    private final Map<Integer, Node> keyNodeMap = new HashMap<>();

    public LRUCache_UseLinkedList_146(int capacity) {

        this.capacity = capacity;
    }

    public int get(int key) {
        Node node = keyNodeMap.get(key);
        if (node != null) {
            caches.remove(node);
            caches.addFirst(node);
            return node.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        Node node = new Node(key, value);
        Node oldNode = keyNodeMap.put(key, node);
        caches.addFirst(node);
        if (caches.size() > capacity) {
            if (oldNode != null) {
                caches.remove(oldNode);
            } else {
                keyNodeMap.remove(caches.getLast().key);
                caches.removeLast();
            }
        }
    }

    private class Node {
        private final int key;
        private final int value;

        private Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }


    public static void main(String[] args) {
        LRUCache_UseLinkedList_146 cache = new LRUCache_UseLinkedList_146(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));
        cache.put(3, 3);
        System.out.println(cache.get(2));
        cache.put(4, 4);
        cache.put(4, 4);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(4, 4);
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
    }
}
