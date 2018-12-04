package com.sun.xiaotian.demo.redis.redis_cli;

import com.sun.xiaotian.demo.redis.util.FileReadUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class RedisLuaScript {

    private static final JedisPool jedisPool = new JedisPool("192.168.0.201", 6379);

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
//        executeHello();
        useGeo();
    }


    private static void executeHello() throws IOException, NoSuchAlgorithmException {
        try (Jedis jedis = jedisPool.getResource()) {
            byte[] script = FileReadUtil.readFromResource("hello.lua");
            String scriptSHA1 = SHA1(script);
            Object response;
            try {
                response = jedis.evalsha(scriptSHA1, 2, "name", "age", "1", "2");
                System.out.println("run sha");
            } catch (Exception e) {
                response = jedis.eval(new String(script), 2, "name", "age", "1", "2");
                System.out.println("run script");
            }
            System.out.println(response);
        }
    }

    private static void useGeo() throws IOException, NoSuchAlgorithmException {
        try (Jedis jedis = jedisPool.getResource()) {
            byte[] script = FileReadUtil.readFromResource("use_geo.lua");
            Object response = jedis.eval(new String(script));
            System.out.println(response);
        }
    }

    private static String SHA1(byte[] script) throws NoSuchAlgorithmException {
        MessageDigest sha_1 = MessageDigest.getInstance("SHA-1");
        String result = DatatypeConverter.printHexBinary(sha_1.digest(script)).toLowerCase();
        System.out.println(result);
        return result;
    }
}
