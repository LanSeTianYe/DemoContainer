package com.xiaotian.demo.redis.redis_cli;

import com.xiaotian.demo.redis.util.FileReadUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class FixedLengthStack {

    private static final JedisPool jedisPool = new JedisPool("192.168.0.201", 6379);

    private static final String stack_name = "fixed_length_stack";

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, InterruptedException {
        CompletableFuture.runAsync(() -> {
            try (Jedis jedis = jedisPool.getResource()) {
                while (true) {
                    List<String> values = jedis.lrange(stack_name, 0, -1);
                    System.out.println(values + "length: " + values.size());
                }
            }
        });

        try (Jedis jedis = jedisPool.getResource()) {
            byte[] script = FileReadUtil.readFromResource("fixed_length_stack.lua");
            String scriptSHA1 = SHA1(script);
            Object response;
            for (int i = 0; i < 100; i++) {
                try {
                    response = jedis.evalsha(scriptSHA1, 1, stack_name, "10", "" + i);
                } catch (Exception e) {
                    response = jedis.eval(new String(script), 1, stack_name, "10", "" + i);
                }
                System.out.println(response);
                TimeUnit.SECONDS.sleep(1);
            }
        }
    }


    private static String SHA1(byte[] script) throws NoSuchAlgorithmException {
        MessageDigest sha_1 = MessageDigest.getInstance("SHA-1");
        String result = DatatypeConverter.printHexBinary(sha_1.digest(script)).toLowerCase();
        System.out.println(result);
        return result;
    }
}
