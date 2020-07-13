package com.ibgdn.zipkin01.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Zipkin Controller
 */
@RestController
public class ZipkinController {
    private static final Logger logger = LoggerFactory.getLogger(ZipkinController.class);

    @GetMapping("/zipkin01")
    public String zipkin01(String name) {
        logger.info("Class: [{}], Method: [{}], Hello {}!",
                this.getClass().getName(), Thread.currentThread().getStackTrace()[1].getMethodName(), name);
        return "Class: " + this.getClass().getName() + ", Method: " + Thread.currentThread().getStackTrace()[1].getMethodName()
                + ", Hello " + name + "!";
    }
}
