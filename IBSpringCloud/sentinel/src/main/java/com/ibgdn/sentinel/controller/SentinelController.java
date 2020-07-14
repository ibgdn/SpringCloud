package com.ibgdn.sentinel.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Sentinel Controller
 */
@RestController
public class SentinelController {

    /**
     * sentinel 调用方法
     *
     * @return String 返回信息
     */
    @GetMapping("/sentinel")
    public String sentinel() {
        return "Class: " + this.getClass().getName() + ", Method: " + Thread.currentThread().getStackTrace()[1].getMethodName()
                + ", Sentinel.";
    }
}
