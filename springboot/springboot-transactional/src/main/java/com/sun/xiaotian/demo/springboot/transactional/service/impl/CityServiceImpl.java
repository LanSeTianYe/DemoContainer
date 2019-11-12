package com.sun.xiaotian.demo.springboot.transactional.service.impl;

import com.sun.xiaotian.demo.springboot.transactional.mapper.CityMapper;
import com.sun.xiaotian.demo.springboot.transactional.model.City;
import com.sun.xiaotian.demo.springboot.transactional.model.CityExample;
import com.sun.xiaotian.demo.springboot.transactional.service.CityService;
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
}
