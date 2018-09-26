package com.sun.xiaotain.demo.springcloud.eureka.client.web;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@RestController
@RequestMapping("name")
public class NameController {

    private final RestTemplate restTemplate;

    public NameController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("getNames")
    public List getNames() {
        List nameList = restTemplate.getForObject("http://EUREKA-SERVER/name/list", List.class);
        return nameList;
    }
}
