package com.xiaotian.demo.springboot.transaction.mapper;

import com.xiaotian.demo.springboot.transaction.model.City;
import com.xiaotian.demo.springboot.transaction.model.CityExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * CityMapper继承基类
 */
@Mapper
public interface CityMapper extends MyBatisBaseMapper<City, Integer, CityExample> {
    City selectById(@Param("id") Integer id);
}