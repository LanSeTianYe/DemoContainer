package com.sun.xiaotain.demo.mybatis.mapper;

import com.sun.xiaotain.demo.mybatis.service.CityService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class CityMapperTest {

    private final static Logger logger = LogManager.getLogger(CityMapperTest.class);

    @Autowired
    private CityMapper cityMapper;

    @Autowired
    private CityService cityService;

    @Test
    public void findByState() throws Exception {
        logger.info(cityMapper.findByState("CA"));
        logger.info(cityService.getCityByStatus("CA"));
    }
}