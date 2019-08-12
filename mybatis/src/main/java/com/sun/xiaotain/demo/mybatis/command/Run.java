package com.sun.xiaotain.demo.mybatis.command;


import com.sun.xiaotain.demo.mybatis.model.City;
import com.sun.xiaotain.demo.mybatis.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class Run implements CommandLineRunner {

    @Autowired
    private CityService cityService;

    @Override
    public void run(String... args) throws Exception {
        while (true) {
            List<City> cityByStatus = cityService.getCityByStatus("1");
            City city = cityService.byId(1);

            System.out.println(city);
            cityByStatus.get(0).getDateTime().plus(100, ChronoUnit.DAYS);
            TimeUnit.SECONDS.sleep(2);
        }
    }
}
