package com.sun.xiaotian.demo.redis.redis_cli;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.io.IOException;

public class RedisList {

    public static void main(String[] args) throws IOException {
        String listName = "test_list";
        JedisCluster cluster = new JedisCluster(HostAndPort.parseString("192.168.0.201:7000"));

        System.out.println(cluster.exists(listName));
        //当key存在的时候设置
        Long no_set = cluster.lpushx(listName, "no set");
        System.out.println(no_set);

        for (int i = 0; i < 300; i++) {
            String value = String.valueOf(i);
            cluster.lpush(listName, value);
            //保证集合只包含3个元素
            cluster.ltrim(listName, 0, 2);
            //保证第一个元素是0
            cluster.lset(listName, -1, "0");
        }
        //元素右移
        System.out.println(cluster.rpoplpush(listName, listName));
        System.out.println(cluster.rpoplpush(listName, listName));
        System.out.println(cluster.rpoplpush(listName, listName));

        //包含
        System.out.println(cluster.lrange(listName, 0, 2));
        System.out.println("length: " + cluster.llen(listName));
        for (int i = 0; i < 3; i++) {
            String result = cluster.lpop(listName);
            System.out.println(result);
        }
    }
}
