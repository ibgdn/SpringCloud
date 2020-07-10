package com.ibgdn.configclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spring Cloud Config Client。
 */
@RestController
public class ConfigClientController {
    @Value("${javaboy}")
    String javaboy;

    /**
     * 调用接口获取配置信息
     *
     * @return 配置信息
     */
    @GetMapping("/configClient")
    public String configClient() {
        return javaboy;
    }
}
