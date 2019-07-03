package com.xiaotian.datasource.dynamic.table;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Slf4j
@Component
public class TableCountUtil {

    private final JdbcTemplate jdbcTemplate;

    private final String querySQL;

    public TableCountUtil(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.querySQL = "select count(1) from information_schema.tables where table_name = ? and table_schema = ?;";
    }

    public int countTable(String tableName, String tableScheme) {
        List<Integer> result = jdbcTemplate.query(querySQL, ps -> {
            ps.setString(1, tableName);
            ps.setString(2, tableScheme);
        }, (rs, rowNum) -> rs.getInt(1));

        return CollectionUtils.isEmpty(result) ? 0 : result.get(0);
    }
}

