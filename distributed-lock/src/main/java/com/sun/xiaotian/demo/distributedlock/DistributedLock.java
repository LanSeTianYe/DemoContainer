package com.sun.xiaotian.demo.distributedlock;

/**
 * 分布式锁接口
 */
public interface DistributedLock {

    public boolean getLock(LockInfo lockInfo);

    public boolean releaseLock(LockInfo lockInfo);

}
