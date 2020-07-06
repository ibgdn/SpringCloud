package com.ibgdn.openfeign.controller;

import com.ibgdn.openfeign.OpenFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * OpenFeign Controller
 */
@RestController
public class OpenFeignController {
    @Autowired
    OpenFeignService openFeignService;

    /**
     * OpenFeign Controller 层 Get 接口
     *
     * @return 调用 service 的接口
     */
    @GetMapping("/OpenFeignControllerGet")
    public String openFeignControllerGet() {
        return openFeignService.openFeignServiceGet();
    }
}
