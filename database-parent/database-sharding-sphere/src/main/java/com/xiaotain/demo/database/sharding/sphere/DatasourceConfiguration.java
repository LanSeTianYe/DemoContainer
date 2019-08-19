package com.xiaotain.demo.database.sharding.sphere;

import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Map;

@Configuration
public class DatasourceConfiguration {

    @Bean
    public DataSource dataSource() throws SQLException {
        DataSource dataSource = ShardingDataSourceFactory.createDataSource(dataSourceMap(), shardingRuleConfiguration(), null);
        return dataSource;
    }


    public Map<String, DataSource> dataSourceMap() {
        return null;
    }


    public ShardingRuleConfiguration shardingRuleConfiguration() {
        return null;
    }

}
