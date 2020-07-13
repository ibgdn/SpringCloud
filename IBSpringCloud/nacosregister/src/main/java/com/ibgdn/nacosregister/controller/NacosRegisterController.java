package com.ibgdn.nacosregister.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Nacos 作为注册中心
 */
@RestController
public class NacosRegisterController {

    @Value("${server.port}")
    String port;

    @GetMapping("/nacosRegister")
    public String nacosRegister() {
        return "Class: " + this.getClass().getName() + ", Method: " + Thread.currentThread().getStackTrace()[1].getMethodName()
                + ", port: " + port;
    }
}
