package com.ibgdn.resilience4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Resilience4jRateLimiterService {
    @Autowired
    RestTemplate restTemplate;

    public String Resilience4jGet() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            stringBuilder.append("Resilience Rate Limiter [ ").append(i + 1).append(" ]: ")
                    .append(restTemplate.getForObject("http://localhost:1120/ProviderResilience4jRateLimiter", String.class))
                    .append("\n");
        }
        return stringBuilder.toString();
    }
}
