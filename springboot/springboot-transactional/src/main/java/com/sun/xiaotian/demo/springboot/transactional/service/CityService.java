package com.sun.xiaotian.demo.springboot.transactional.service;

import com.sun.xiaotian.demo.springboot.transactional.model.City;

import java.util.List;


public interface CityService {
    List<City> getCityByStatus(String status);

    City byId(Integer id);

    void add(City city);
}
