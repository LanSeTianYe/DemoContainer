package com.sun.xiaotian.demo.distributedlock;

import com.sun.xiaotian.demo.distributedlock.exception.DistributedLockException;
import com.sun.xiaotian.demo.distributedlock.util.JDBCConnectPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * JDBC乐观锁，基于版本号实现业务逻辑
 */


/**
 * 数据库脚本
 * DROP TABLE IF EXISTS `person`;
 * CREATE TABLE `person` (
 * `id` int(11) unsigned zerofill NOT NULL,
 * `name` varchar(255) DEFAULT NULL,
 * `version` bigint(20) unsigned zerofill NOT NULL,
 * `count` bigint(20) unsigned zerofill DEFAULT NULL,
 * PRIMARY KEY (`id`)
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 * <p>
 * INSERT INTO person VALUES(1,'name',1, 1);
 */

public class JDBCOptimisticLocking {

    private final static Logger logger = LoggerFactory.getLogger(JDBCOptimisticLocking.class);

    public static JDBCConnectPool jdbcConnectPool = JDBCConnectPool.getInstance();

    public void updateData() {
        Connection connect = null;
        try {
            long result;
            long version = 0;
            long count = 0;
            try {
                connect = jdbcConnectPool.getConnect();
            } catch (InterruptedException e) {
                logger.error("获取数据库连接失败!", e);
            }

            do {
                PreparedStatement query = connect.prepareStatement("SELECT version, count from person WHERE id = 1");
                ResultSet resultSet = query.executeQuery();

                if (resultSet.next()) {
                    version = resultSet.getLong(1);
                    count = resultSet.getLong(2);
                }

                PreparedStatement update = connect.prepareStatement("update person set count = ?, version = ? WHERE id = 1 AND version = ?");
                update.setLong(1, count + 1);
                update.setLong(2, version + 1);
                update.setLong(3, version);
                result = update.executeLargeUpdate();
            } while (result != 1);
            logger.info("update version: " + version + " count: " + count);
        } catch (DistributedLockException | SQLException e) {
            logger.error(e.getMessage(), e);
        } finally {
            jdbcConnectPool.reclaim(connect);
        }
    }
}

