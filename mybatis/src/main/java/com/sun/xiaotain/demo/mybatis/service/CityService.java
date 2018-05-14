package com.sun.xiaotain.demo.mybatis.service;

import com.sun.xiaotain.demo.mybatis.model.City;

import java.util.List;


public interface CityService {
    public List<City> getCityByStatus(String status);
}
