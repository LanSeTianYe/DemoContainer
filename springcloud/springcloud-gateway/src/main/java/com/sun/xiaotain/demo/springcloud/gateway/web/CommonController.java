package com.sun.xiaotain.demo.springcloud.gateway.web;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("common")
public class CommonController {


    @GetMapping("error")
    public Mono<String> error() {
        return Mono.just("error");
    }
}
