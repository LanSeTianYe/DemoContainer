package com.xiaotian.demo.database.tidb.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class TestController {

    private final JdbcTemplate jdbcTemplate;

    private static final String SHOW_TABLE_SQL = "SHOW DATABASES";

    public TestController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @RequestMapping("tables")
    @ResponseBody
    public Object tables() {
        List<Map<Integer, String>> result = jdbcTemplate.query(SHOW_TABLE_SQL, ps -> {
        }, (rs, rowNum) -> {
            String tableName = rs.getString(1);
            return Collections.singletonMap(rowNum, tableName);
        });
        log.info("database: {}", result);
        return result;
    }

}
