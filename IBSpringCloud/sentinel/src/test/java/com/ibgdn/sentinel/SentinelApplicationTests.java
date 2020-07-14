package com.ibgdn.sentinel;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@SpringBootTest
class SentinelApplicationTests {

    private static final Logger logger = LoggerFactory.getLogger(SentinelApplicationTests.class);


    @Test
    void contextLoads() {
    }

    /**
     * Sentinel 流量控制测试方法。
     */
    @Test
    void flowControl() {

        RestTemplate restTemplate = new RestTemplate();

        logger.info("Sentinel Flow Control Start.");
        for (int i = 0; i < 20; i++) {
            logger.info("Sentinel Flow Control [{}] Date: [{}] [{}]",
                    i, new Date(), restTemplate.getForObject("http://localhost:1280/sentinel", String.class));
        }
        logger.info("Sentinel Flow Control End.");
    }

}
