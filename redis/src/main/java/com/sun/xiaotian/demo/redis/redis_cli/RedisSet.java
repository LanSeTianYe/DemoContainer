package com.sun.xiaotian.demo.redis.redis_cli;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.Random;
import java.util.Set;

public class RedisSet {

    public static void main(String[] args) {
        String setName1 = "{test}_set1";
        String setName2 = "{test}_set2";
        String setName3 = "{test}_set3";
        String setName4 = "{test}_set4";
        String setName5 = "{test}_set5";

        JedisCluster redis = new JedisCluster(HostAndPort.parseString("192.168.0.201:7000"));
        Random random = new Random(37);

        for (int i = 0; i < 10; i++) {
            redis.sadd(setName1, String.valueOf(random.nextInt(1000)));
            redis.sadd(setName2, String.valueOf(random.nextInt(1000)));
            redis.sadd(setName3, String.valueOf(random.nextInt(1000)));
        }

        System.out.println(redis.smembers(setName1));
        System.out.println(redis.smembers(setName2));
        System.out.println(redis.smembers(setName3));

        redis.sdiffstore(setName4, setName1, setName2, setName3);
        redis.sinterstore(setName5, setName1, setName2, setName3);
        System.out.println("diff: " + redis.smembers(setName4));
        System.out.println("inter: " + redis.smembers(setName5));
        System.out.println("union: " + redis.sunion(setName1, setName2, setName3));
        System.out.println("length: " + redis.scard(setName1));

        System.out.println(redis.srandmember(setName1, 11));
        System.out.println(redis.srandmember(setName1, -11));

        //
        while (true) {
            Set<String> pop = redis.spop(setName1, 3);
            if (pop.size() == 0) {
                break;
            }
            System.out.println(pop);
        }

        //删除元素
        System.out.println("rem: ");
        for (int i = 0; i < 10; i++) {
            redis.srem(setName1, String.valueOf(random.nextInt(1000)));
            redis.srem(setName2, String.valueOf(random.nextInt(1000)));
            redis.srem(setName3, String.valueOf(random.nextInt(1000)));
        }

        //移动元素
        for (int i = 0; i < 1000; i++) {
            redis.smove(setName2, setName1, String.valueOf(random.nextInt(1000)));
        }

        System.out.println(redis.smembers(setName1));
        System.out.println(redis.smembers(setName2));
        System.out.println(redis.smembers(setName3));
    }
}
