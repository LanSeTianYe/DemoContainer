package com.sun.xiaotian.demo.springboot.transactional.mapper;

import com.sun.xiaotian.demo.springboot.transactional.model.City;
import com.sun.xiaotian.demo.springboot.transactional.model.CityExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * CityMapper继承基类
 */
@Mapper
public interface CityMapper extends MyBatisBaseMapper<City, Integer, CityExample> {
    City selectById(@Param("id") Integer id);
}