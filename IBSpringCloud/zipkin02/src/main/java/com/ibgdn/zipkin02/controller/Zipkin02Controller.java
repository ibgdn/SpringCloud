package com.ibgdn.zipkin02.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Zipkin02 Controller
 */
@RestController
public class Zipkin02Controller {
    private static final Logger logger = LoggerFactory.getLogger(Zipkin02Controller.class);

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/zipkin02")
    public String zipkin02() {
        String restTemplateForObject = restTemplate.getForObject("http://localhost:1230/zipkin01?name={1}", String.class, "zipkin02");
        logger.info(restTemplateForObject);
        return restTemplateForObject;
    }
}
