package com.xiaotian.demo.springboot.transactional.biz;


import com.xiaotian.demo.springboot.transactional.exception.TransactionBusinessException;
import com.xiaotian.demo.springboot.transactional.model.City;
import com.xiaotian.demo.springboot.transactional.service.CityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class CityBiz {

    private final CityService cityService;

    public CityBiz(CityService cityService) {
        this.cityService = cityService;
    }

    @Transactional(rollbackFor = Exception.class)
    public void addUserAndExceptionBefore() {
        exception();
        addFirstCity();
        addSecondCity();
    }

    @Transactional
    public void addUserAndExceptionInMiddle() {
        addFirstCity();
        exception();
        addSecondCity();
    }

    @Transactional(rollbackFor = Exception.class)
    public void addUserAndExceptionAfter() {
        addFirstCity();
        addSecondCity();
        exception();
    }

    @Transactional(rollbackFor = Exception.class)
    public void addUserAndNoException() {
        addFirstCity();
        addSecondCity();
    }


    public void useThisInvokeTransactionMethod() {
        addAndThrowException();
    }

    @Transactional
    public void addAndThrowException() {
        City city = new City();
        city.setName("city3");
        city.setCountry("country3");
        city.setState("3");
        city.setDateTime(LocalDateTime.now());
        cityService.add(city);
        throw new IllegalArgumentException("参数非法");
    }

    private void addFirstCity() {
        City city = new City();
        city.setName("city1");
        city.setCountry("country1");
        city.setState("1");
        city.setDateTime(LocalDateTime.now());
        cityService.add(city);
    }

    private void exception() {
        throw new TransactionBusinessException("参数非法");
    }

    private void addSecondCity() {
        City city = new City();
        city.setName("city2");
        city.setCountry("country2");
        city.setState("2");
        city.setDateTime(LocalDateTime.now());
        cityService.add(city);
    }
}
