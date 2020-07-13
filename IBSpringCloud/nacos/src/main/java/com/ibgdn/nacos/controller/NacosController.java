package com.ibgdn.nacos.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Alibaba 服务发现、配置、管理
 */
@RestController
@RefreshScope
public class NacosController {

    @Value("${name}")
    String name;

    /**
     * 获取 nacos 配置文件中 name 属性值，并返回到浏览器页面。
     *
     * @return String 返回数据
     */
    @GetMapping("/nacos")
    public String nacos() {
        return "Class: " + this.getClass().getName() + ", Method: " + Thread.currentThread().getStackTrace()[1].getMethodName()
                + ", name: " + name;
    }
}
