package com.sun.xiaotain.demo.mybatis.mapper;

import com.sun.xiaotain.demo.mybatis.model.City;
import com.sun.xiaotain.demo.mybatis.model.CityExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * CityMapper继承基类
 */
@Mapper
public interface CityMapper extends MyBatisBaseMapper<City, Integer, CityExample> {
    City selectById(@Param("id") Integer id);
}