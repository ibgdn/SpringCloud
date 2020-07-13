package com.ibgdn.nacosconsumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Nacos 作为消费者
 */
@RestController
public class NacosConsumerController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/nacosConsumer")
    public String nacosConsumer() {
        return "Class: " + this.getClass().getName() + ", Method: " + Thread.currentThread().getStackTrace()[1].getMethodName()
                + ", GetForObject: " + restTemplate.getForObject("http://nacosregister/nacosRegister", String.class);
    }

}
