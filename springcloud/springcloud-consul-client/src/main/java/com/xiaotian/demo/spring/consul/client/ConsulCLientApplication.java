package com.xiaotian.demo.spring.consul.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableAutoConfiguration
@EnableDiscoveryClient
@RestController
@EnableFeignClients
public class ConsulCLientApplication {

    @Autowired
    private LoadBalancerClient loadBalancer;

    @Autowired
    private RestTemplate restTemplate;

    private final String appName = "consul-demo";


    @RequestMapping("/")
    public String home() {
        return "Hello World";
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @FeignClient("testConsulApp")
    public interface SampleClient {
        @RequestMapping(value = "/choose", method = RequestMethod.GET)
        String choose();
    }

    @RequestMapping("/choose")
    public String choose() {
        return loadBalancer.choose(appName).getUri().toString();
    }

    @RequestMapping("/request")
    public String request() {
        return this.restTemplate.getForObject("http://" + appName + "/", String.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsulCLientApplication.class, args);
    }
}
