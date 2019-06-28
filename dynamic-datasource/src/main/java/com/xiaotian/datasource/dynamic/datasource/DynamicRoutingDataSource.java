package com.xiaotian.datasource.dynamic.datasource;

import com.alibaba.fastjson.JSON;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.HashMap;

@Slf4j
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {

    public DynamicRoutingDataSource(DataSourceProperties... dataSourceProperties) {
        log.info("build DynamicRoutingDataSource start");
        HashMap<Object, Object> datasourceMap = new HashMap<>();
        for (DataSourceProperties dataSourceProperty : dataSourceProperties) {
            ThreadDataSourceNameHolder.set(dataSourceProperty.getName());
            log.info("build DynamicRoutingDataSource {}", JSON.toJSONString(dataSourceProperty));
            HikariDataSource dataSource = dataSourceProperty.initializeDataSourceBuilder().type(HikariDataSource.class).build();
            datasourceMap.put(dataSourceProperty.getName(), dataSource);
        }
        setTargetDataSources(datasourceMap);
        log.info("build DynamicRoutingDataSource end");
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return ThreadDataSourceNameHolder.get();
    }
}
