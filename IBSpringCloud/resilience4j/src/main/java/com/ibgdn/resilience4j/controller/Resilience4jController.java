package com.ibgdn.resilience4j.controller;

import com.ibgdn.resilience4j.service.Resilience4jCircuitBreakerService;
import com.ibgdn.resilience4j.service.Resilience4jRateLimiterService;
import com.ibgdn.resilience4j.service.Resilience4jReTryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Resilience4j 接口
 */
@RestController
public class Resilience4jController {
    @Autowired
    Resilience4jReTryService resilience4JReTryService;

    @Autowired
    Resilience4jCircuitBreakerService resilience4jCircuitBreakerService;

    @Autowired
    Resilience4jRateLimiterService resilience4JRateLimiterService;

    /**
     * Resilience4j ReTry 接口
     *
     * @return String 返回字符串
     */
    @GetMapping("/resilience4jReTry")
    public String resilience4jReTry() {
        return resilience4JReTryService.Resilience4jGet();
    }


    /**
     * Resilience4j CircuitBreaker 接口
     *
     * @return String 返回字符串
     */
    @GetMapping("/resilience4jCircuitBreaker")
    public String resilience4jCircuitBreaker() {
        return resilience4jCircuitBreakerService.Resilience4jGet();
    }

    /**
     * Resilience4j Rate Limiter 接口
     *
     * @return String 返回字符串
     */
    @GetMapping("/resilience4jRateLimiter")
    public String resilience4jRateLimiter() {
        return resilience4JRateLimiterService.Resilience4jGet();
    }
}
