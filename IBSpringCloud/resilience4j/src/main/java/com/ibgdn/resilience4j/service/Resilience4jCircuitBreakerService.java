package com.ibgdn.resilience4j.service;

import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Retry(name = "circuitBreakerA", fallbackMethod = "circuitBreakerFallBack")
public class Resilience4jCircuitBreakerService {
    @Autowired
    RestTemplate restTemplate;

    public String Resilience4jGet() {
        return restTemplate.getForObject("http://localhost:1120/ProviderResilience4jReTry", String.class);
    }

    /**
     * 断路器降级方法
     * 需要和正常调用方法的返回值、参数列表一致，同时需要加入 Throwable。
     * <p>
     * 调用 Provider 出现错误的接口，降级调用此方法，浏览器展示内容：
     * Class: com.ibgdn.resilience4j.service.Resilience4jCircuitBreakerService, Method: circuitBreakerFallBack
     */
    public String circuitBreakerFallBack(Throwable t) {
        return "Class: " + this.getClass().getName()
                + ", Method: " + Thread.currentThread().getStackTrace()[1].getMethodName();
    }
}
