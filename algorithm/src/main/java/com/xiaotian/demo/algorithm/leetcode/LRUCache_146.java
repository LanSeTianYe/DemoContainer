package com.xiaotian.demo.algorithm.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * https://leetcode.com/problems/lru-cache/submissions/
 * LRU cache
 * 实现方法1：LinkedHashMap 的 removeEldestEntry 方法
 * 实现方法2：自己维护 LinkedList 列表， Map 维护节点和key之间的关系，通过控制List 控制节点删除和插入
 * 实现方法3：Map维护缓存数据，Queue维护访问历史，删除的时候出队比较然后删除
 */
public class LRUCache_146 {

    private long currAccessId = 0;

    /**
     * 缓存容量
     */
    private final int capacity;

    /**
     * 缓存Map
     */
    private final Map<Integer, CacheObject> cache;

    /**
     * 缓存key
     */
    private final Queue<CacheLog> cacheLogs;


    public LRUCache_146(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>(capacity + 1);
        this.cacheLogs = new LinkedList<>();
    }

    public int get(int key) {
        long accessId = currAccessId++;
        CacheObject cacheObject = this.cache.get(key);
        if (null == cacheObject) {
            return -1;
        }
        this.cacheLogs.add(new CacheLog(key, accessId));
        this.cache.put(key, new CacheObject(cacheObject.value, accessId));
        return cacheObject.value;
    }

    public void put(int key, int value) {
        long accessId = currAccessId++;
        this.cacheLogs.add(new CacheLog(key, accessId));
        cache.put(key, new CacheObject(value, accessId));
        if (cache.size() > capacity) {
            do {
                CacheLog pollResult = cacheLogs.poll();
                if (pollResult != null && cache.get(pollResult.key) != null && pollResult.accessId == cache.get(pollResult.key).accessId) {
                    cache.remove(pollResult.key);
                    return;
                }
            } while (true);
        }
    }

    /**
     * 缓存缓存日志
     */
    private class CacheLog {
        private final int key;
        private final long accessId;

        private CacheLog(int key, long accessId) {
            this.key = key;
            this.accessId = accessId;
        }
    }

    /**
     * 缓存对象，用时间表示最新
     */
    private class CacheObject {
        private final int value;
        private final long accessId;

        private CacheObject(int value, long accessId) {
            this.value = value;
            this.accessId = accessId;
        }
    }

    public static void main(String[] args) {
        LRUCache_146 cache = new LRUCache_146(2);
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
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
    }
}
