package com.xiaotian.demo.springboot.transactional.runner;

import com.xiaotian.demo.springboot.transactional.component.BeanComponent;
import com.xiaotian.demo.springboot.transactional.mapper.CityMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TestBenRunner implements CommandLineRunner {

    private final BeanComponent beanComponent;

    public TestBenRunner(BeanComponent beanComponent) {
        this.beanComponent = beanComponent;
    }

    @Override
    public void run(String... args) {
        CityMapper cityMapper = beanComponent.get(CityMapper.class);
        System.out.println(cityMapper.selectById(0));
    }
}
