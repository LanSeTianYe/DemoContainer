package com.sun.xiaotian.demo.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

import java.io.IOException;
import java.util.List;

public class TestPipeline {

    static int count = 100000;

    private static final Jedis jedis = new Jedis("127.0.0.1", 6379);

    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        Pipeline pipelined = jedis.pipelined();
        pipelined.multi();
        for (int i = 0; i < count; i++) {
            pipelined.ttl("name");
        }
        pipelined.sync();
        pipelined.syncAndReturnAll();
        Response<List<Object>> exec = pipelined.exec();
        pipelined.close();
        System.out.println(System.currentTimeMillis() - startTime);

        startTime = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            jedis.get("name");
        }
        System.out.println(System.currentTimeMillis() - startTime);
    }
}
