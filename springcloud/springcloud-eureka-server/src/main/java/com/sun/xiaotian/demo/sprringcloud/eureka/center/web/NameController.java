package com.sun.xiaotian.demo.sprringcloud.eureka.center.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("name")
public class NameController {

    private static final Logger logger = LogManager.getLogger(NameController.class);

    @GetMapping("list")
    public List<String> list() {
        List<String> result = new ArrayList<>();
        result.add("zhangsan");
        result.add("lisi");
        result.add("wangwu");
        logger.info(result);
        return result;
    }
}
