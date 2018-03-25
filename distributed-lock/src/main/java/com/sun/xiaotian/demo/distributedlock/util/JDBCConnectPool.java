package com.sun.xiaotian.demo.distributedlock.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class JDBCConnectPool {

    private JDBCConnectPool() {
    }

    public static JDBCConnectPool getInstance() {
        return Inner.jdbcConnectPool;
    }

    private final static Logger logger = LoggerFactory.getLogger(JDBCConnectPool.class);

    int maxConnectSize = 50;
    int currSize = 0;

    private BlockingQueue<Connection> connections = new ArrayBlockingQueue<Connection>(50);

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage(), e);
            System.exit(0);
        }
    }

    /**
     * 获取数据库连接
     *
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public synchronized Connection getConnect() throws InterruptedException {
        if (currSize < maxConnectSize) {
            Connection connection = null;
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8&useSSL=false", "root", "000000");
            } catch (SQLException e) {
                logger.error(e.getMessage(), e);
            }
            currSize++;
            connections.add(connection);
        }
        return connections.take();
    }

    /**
     * 回收已经打开的连接池
     */
    public void reclaim(Connection connection) {
        try {
            connections.put(connection);
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
        }
        logger.info("当前数据库连接池大小 :" + connections.size());
    }

    static class Inner {

        private static JDBCConnectPool jdbcConnectPool;

        static {
            jdbcConnectPool = new JDBCConnectPool();
        }
    }
}
