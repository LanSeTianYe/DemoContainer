package com.sun.xiaotain.demo.mybatis.mapper;

import com.sun.xiaotain.demo.mybatis.model.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CityMapper {

    @Select("SELECT id, name, state, country, date_time as dateTime FROM city WHERE state = #{state}")
    List<City> findByState(String state);
}
