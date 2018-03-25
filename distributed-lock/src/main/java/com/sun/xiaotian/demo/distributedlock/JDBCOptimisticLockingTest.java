package com.sun.xiaotian.demo.distributedlock;

import com.sun.xiaotian.demo.distributedlock.util.JDBCConnectPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JDBCOptimisticLockingTest {

    private final static Logger logger = LoggerFactory.getLogger(JDBCOptimisticLockingTest.class);

    public static void main(String[] args) throws InterruptedException, SQLException {

        int threadSize = 100;

        //模拟多线程
        ExecutorService executorService = Executors.newCachedThreadPool();
        CountDownLatch downLatch = new CountDownLatch(threadSize);

        JDBCConnectPool jdbcConnectPool = JDBCConnectPool.getInstance();

        Connection connect = jdbcConnectPool.getConnect();
        PreparedStatement query = connect.prepareStatement("SELECT count FROM person");
        ResultSet resultSet = query.executeQuery();
        resultSet.next();
        long oldCount = resultSet.getLong(1);


        for (int i = 0; i < threadSize; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    JDBCOptimisticLocking jdbcOptimisticLocking = new JDBCOptimisticLocking();
                    jdbcOptimisticLocking.updateData();
                    downLatch.countDown();
                }
            });
        }
        downLatch.await();

        ResultSet resultSet1 = query.executeQuery();
        resultSet1.next();
        long newCount = resultSet1.getLong(1);

        System.out.println(newCount - oldCount == threadSize);
        executorService.shutdownNow();
    }
}
