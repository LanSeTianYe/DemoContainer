package com.sun.xiaotian.demo.redis;

import redis.clients.jedis.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TestJedis {

    private static final Jedis jedis = new Jedis("127.0.0.1", 6379);

    public static void main(String[] args) throws InterruptedException {
        testPing();
        testString();
        testList();
        testHash();
        testSet();
        testSortedSet();
    }

    private static void testPing() {
        System.out.println(jedis.ping());
    }

    private static void testString() throws InterruptedException {
        String key = "name";
        Boolean existName = jedis.exists(key);
        if (existName) {
            jedis.del(key);
        }
        String setResult = jedis.set(key, "sunfeilong");
        System.out.println("setResult:" + setResult);
        System.out.println(key + " : " + jedis.get(key));

        Long setNoExists = jedis.setnx(key, "xiaotian");
        if (setNoExists == 0) {
            System.out.println("设置失败!");
        }

        String setexResult = jedis.setex(key, 10, "xiaotian");
        System.out.println(setexResult);

        Long ttl = jedis.ttl(key);
        System.out.println("expireTime: " + ttl);
    }

    private static void testList() {

        String key1 = "test_list1";
        String key2 = "test_list2";
        if (jedis.exists(key1)) {
            jedis.del(key1);
        }
        if (jedis.exists(key2)) {
            jedis.del(key2);
        }

        jedis.lpush(key1, "a1");
        jedis.lpush(key1, "b1");
        jedis.lpush(key1, "c1");
        jedis.lpush(key1, "d1");

        jedis.lpush(key2, "d2");
        jedis.lpush(key2, "c2");
        jedis.lpush(key2, "b2");
        jedis.lpush(key2, "a2");
        jedis.lpush(key2, "e2");
        jedis.lpush(key2, "e2");

        jedis.lrem(key2, 2, "e2");

        List<String> rangeList = jedis.lrange(key2, 0, jedis.llen(key2));
        System.out.println("rangeList[0,2]: " + rangeList);
        String first = jedis.lindex(key1, 0);
        String outOfRange = jedis.lindex(key1, 10);
        System.out.println("first: " + first);
        System.out.println("outOfRange: " + outOfRange);

        System.out.println(jedis.brpoplpush(key2, key1, 1));
        System.out.println(jedis.brpoplpush(key2, key1, 1));
        System.out.println(jedis.brpoplpush(key2, key1, 1));
        System.out.println(jedis.brpoplpush(key2, key1, 1));

        jedis.linsert(key1, BinaryClient.LIST_POSITION.AFTER, "d2", "e");

        System.out.println("length:" + jedis.llen(key1));

        System.out.println(jedis.blpop(1, key1));
        System.out.println(jedis.blpop(1, key1));
        System.out.println(jedis.blpop(1, key1));
        System.out.println(jedis.blpop(1, key1));

        System.out.println(jedis.brpop(1, key1));
        System.out.println(jedis.brpop(1, key1));
        System.out.println(jedis.brpop(1, key1));
        System.out.println(jedis.brpop(1, key1));
        System.out.println(jedis.blpop(1, key1));

        jedis.lpush(key1, "a1");
        jedis.lpush(key1, "b1");
        jedis.lpush(key1, "c1");
        jedis.lpush(key1, "d1");

        jedis.lpush(key2, "d2");
        jedis.lpush(key2, "c2");
        jedis.lpush(key2, "b2");
        jedis.lpush(key2, "a2");

        jedis.expire(key1, 10);
        jedis.expire(key2, 10);
    }

    private static void testHash() {
        String key = "person_1";

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("name", "sunfeilong");
        hashMap.put("age", "24");
        for (int i = 0; i < 1000; i++) {
            hashMap.put("temp" + i, "");
        }
        jedis.hmset(key, hashMap);
        System.out.println(jedis.hlen(key));
        System.out.println("name field exists: " + jedis.hexists(key, "name"));
        System.out.println("name hdel: " + jedis.hdel(key, "name"));
        System.out.println("age hIncrease: " + jedis.hincrBy(key, "age", 1L));
        System.out.println("age hincrByFloat: " + jedis.hincrByFloat(key, "age", 3.002D));
        System.out.println("hkeys: " + jedis.hkeys(key));
        System.out.println("hkeys: " + jedis.hmget(key, "name", "age", "address"));
        System.out.println("hvals: " + jedis.hvals(key));
        Long hsetnx = jedis.hsetnx(key, "name", "xiaotian");
        System.out.println("hsetnx: " + hsetnx);

        Map<String, String> hgetAll = jedis.hgetAll(key);
        System.out.println(hgetAll.get("name"));
        System.out.println(hgetAll.get("age"));
        System.out.println(hgetAll.get("sex"));

        ScanResult<Map.Entry<String, String>> result = jedis.hscan(key, "");
        String cursor = result.getStringCursor();
        System.out.println(result.getResult());
        while (!"0".equals(cursor)) {
            result = jedis.hscan(key, cursor);
            System.out.println(result.getResult());
            cursor = result.getStringCursor();
        }

        jedis.expire(key, 1);
    }

    private static void testSet() {
        String key1 = "test_set_1";
        String key2 = "test_set_2";
        String key3 = "test_set_3";
        String key4 = "test_set_4";
        String key5 = "test_set_5";

        jedis.sadd(key1, "a");
        jedis.sadd(key1, "b");
        jedis.sadd(key1, "c");
        jedis.sadd(key1, "d");

        jedis.sadd(key2, "c");
        jedis.sadd(key2, "d");
        jedis.sadd(key2, "e");
        jedis.sadd(key2, "f");

        System.out.println("test_set_1: " + jedis.smembers(key1));
        System.out.println("scard: " + jedis.scard(key1));
        System.out.println("sdiff: " + jedis.sdiff(key1, key2));
        System.out.println("sdiffstore: " + jedis.sdiffstore(key3, key1, key2));
        System.out.println("sinter: " + jedis.sinter(key1, key2));
        System.out.println("sinterstore: " + jedis.sinterstore(key4, key1, key2));
        System.out.println("sismember: " + jedis.sismember(key4, "e"));
        System.out.println(jedis.smove(key1, key2, "a"));
        System.out.println("test_set_1: " + jedis.smembers(key1));
        System.out.println("spop: " + jedis.spop(key1));
        System.out.println("test_set_1: " + jedis.smembers(key1));
        System.out.println("srandmember: " + jedis.srandmember(key1));
        System.out.println("srem: " + jedis.srem(key1, "a", "b"));
        System.out.println("sunion: " + jedis.sunion(key1, key2, key3, key4));

        for (int i = 0; i < 1000; i++) {
            jedis.sadd(key5, i + "");
        }

        ScanParams scanParams = new ScanParams();
        scanParams.count(100);
        ScanResult<String> sscan = jedis.sscan(key5, "", scanParams);
        System.out.println(sscan.getResult());
        while (!"0".equals(sscan.getStringCursor())) {
            sscan = jedis.sscan(key5, sscan.getStringCursor(), scanParams);
            System.out.println(sscan.getResult());
        }
    }

    private static void testSortedSet() {
        String key1 = "test_sorted_set_1";
        String key2 = "test_sorted_set_2";
        String key3 = "test_sorted_set_3";
        String key4 = "test_sorted_set_4";
        String key5 = "test_sorted_set_5";

        jedis.zadd(key1, 1, "a");
        jedis.zadd(key1, 1, "aa");
        jedis.zadd(key1, 2, "b");
        jedis.zadd(key1, 2, "bb");
        jedis.zadd(key1, 3, "c");
        jedis.zadd(key1, 4, "d");

        jedis.zadd(key2, 1, "c");
        jedis.zadd(key2, 2, "d");
        jedis.zadd(key2, 3, "e");
        jedis.zadd(key2, 4, "f");


        System.out.println("zcard: " + jedis.zcard(key1));
        System.out.println("zcount: " + jedis.zcount(key1, 0, 2));
        System.out.println("zrange: " + jedis.zrange(key1, 0, 100));
        System.out.println("zinterstore: " + jedis.zinterstore(key3, key1, key2));
        System.out.println("zrange: " + jedis.zrange(key3, 0, 100));
        //字典区间语法待探索
        System.out.println("zlexcount: " + jedis.zlexcount(key1, "[a", "[bb"));
        System.out.println("zlexcount: " + jedis.zlexcount(key1, "(a", "[bb"));

        System.out.println("zincrby: " + jedis.zincrby(key1, 1D, "a"));

        for (int i = 0; i < 1000; i++) {
            jedis.zadd(key5, i, "aa_" + i);
        }

        ScanResult<Tuple> result = jedis.zscan(key5, "");
        for (Tuple tuple : result.getResult()) {
            System.out.print(tuple.getElement()+" : "+tuple.getScore());
        }
        while (! "0".equals(result.getStringCursor())) {
            result = jedis.zscan(key5, result.getStringCursor());
            for (Tuple tuple : result.getResult()) {
                System.out.print(tuple.getElement()+" : "+tuple.getScore());
            }
            System.out.println();
        }
    }
}
