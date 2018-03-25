package com.sun.xiaotian.demo.distributedlock;

import com.sun.xiaotian.demo.distributedlock.util.JsonParseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.util.concurrent.TimeUnit;


/**
 * 基于Redis实现分布式锁。存在的问题：由于使用过期时间，当key的过期时间达到，
 * 但是该操作还没有执行完，key被删除，其他系统可以取到锁，
 * 此时会出现，多个系统同时操作数据的情况。
 */
public class RedisDistributedLock implements DistributedLock {

    private final static Logger logger = LoggerFactory.getLogger(RedisDistributedLock.class);

    private final Jedis jedis = new Jedis("127.0.0.1");

    @Override
    public boolean getLock(LockInfo lockInfo) {
        Long result;
        //如果key的过期时间小于零，重新指定过期时间
        if (jedis.exists(lockInfo.getResourcesId()) && jedis.ttl(lockInfo.getResourcesId()) < 0) {
            jedis.expire(lockInfo.getResourcesId(), lockInfo.getExpireDate());
        }

        //循环执行耗内存
        logger.info("尝试获取锁:" + lockInfo.getThreadId());
        while (jedis.setnx(lockInfo.getResourcesId(), JsonParseUtil.writeToJson(lockInfo)) != 1) {
            try {
                //等待锁被释放
                TimeUnit.MICROSECONDS.sleep(50);
            } catch (InterruptedException e) {
                logger.info("获取锁失败，等待时出现异常:" + lockInfo.getThreadId());
                return false;
            }
        }
        //设置过期时间，防止系统宕机之后，数据在Redis中没有被删除而造成的死锁问题。
        //但是如果系统 在 jedis.setnx 执行完成，下一步 jedis.expire 执行之前宕机，同样会产生死锁问题
        //上面的 如果key的过期时间小于零，重新指定过期时间 可以解决该问题

        jedis.expire(lockInfo.getResourcesId(), lockInfo.getExpireDate());

        logger.info("获取锁成功:" + lockInfo.getThreadId());
        return true;
    }

    @Override
    public boolean releaseLock(LockInfo lockInfo) {
        logger.info("释放锁:" + lockInfo.getThreadId());
        Long del = jedis.del(lockInfo.getResourcesId());
        //关闭连接
        jedis.close();
        return del == 1;
    }
}
