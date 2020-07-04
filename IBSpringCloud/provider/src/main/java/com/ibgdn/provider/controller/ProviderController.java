package com.ibgdn.provider.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务注册接口
 */
@RestController
public class ProviderController {
    @GetMapping("/provider")
    public String provider() {
        return "Provider Controller.";
    }
}
