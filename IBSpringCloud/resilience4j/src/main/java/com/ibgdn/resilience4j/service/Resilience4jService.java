package com.ibgdn.resilience4j.service;

import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Retry(name = "retryA")
public class Resilience4jService {
    @Autowired
    RestTemplate restTemplate;

    public String Resilience4jGet() {
        return restTemplate.getForObject("http://localhost:1120/ProviderResilience4jReTry", String.class);
    }
}
