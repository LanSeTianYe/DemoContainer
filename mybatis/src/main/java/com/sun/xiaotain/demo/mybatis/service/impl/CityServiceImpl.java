package com.sun.xiaotain.demo.mybatis.service.impl;

import com.sun.xiaotain.demo.mybatis.mapper.CityMapper;
import com.sun.xiaotain.demo.mybatis.model.City;
import com.sun.xiaotain.demo.mybatis.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService{

    @Autowired
    private final CityMapper cityMapper;

    public CityServiceImpl(CityMapper cityMapper) {
        this.cityMapper = cityMapper;
    }

    @Override
    public List<City> getCityByStatus(String status) {
        return cityMapper.findByState(status);
    }
}
