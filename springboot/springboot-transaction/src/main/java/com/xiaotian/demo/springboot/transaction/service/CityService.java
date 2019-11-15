package com.xiaotian.demo.springboot.transaction.service;

import com.xiaotian.demo.springboot.transaction.model.City;

import java.util.List;


public interface CityService {
    List<City> getCityByStatus(String status);

    City byId(Integer id);

    void add(City city);
}
