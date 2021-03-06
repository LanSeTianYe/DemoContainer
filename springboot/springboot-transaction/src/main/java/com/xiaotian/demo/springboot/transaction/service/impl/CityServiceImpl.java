package com.xiaotian.demo.springboot.transaction.service.impl;

import com.xiaotian.demo.springboot.transaction.mapper.CityMapper;
import com.xiaotian.demo.springboot.transaction.model.City;
import com.xiaotian.demo.springboot.transaction.model.CityExample;
import com.xiaotian.demo.springboot.transaction.service.CityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    private final CityMapper cityMapper;

    public CityServiceImpl(CityMapper cityMapper) {
        this.cityMapper = cityMapper;
    }

    @Override
    public List<City> getCityByStatus(String status) {
        CityExample cityExample = new CityExample();
        CityExample.Criteria criteria = cityExample.createCriteria();
        criteria.andStateEqualTo(status);
        return cityMapper.selectByExample(cityExample);
    }

    @Override
    public City byId(Integer id) {
        return cityMapper.selectById(id);
    }

    @Override
    public void add(City city) {
        cityMapper.insert(city);
    }
}
