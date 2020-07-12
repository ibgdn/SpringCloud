package com.ibgdn.sleuth.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class SleuthService {

    private static final Logger logger = LoggerFactory.getLogger(SleuthService.class);

    // 添加异步注解，执行异步操作
    @Async
    public String sleuthService() {
        String str = "sleuthService";
        logger.info("Class: [{}], Method: [{}], Info: [{}].",
                this.getClass().getName(), Thread.currentThread().getStackTrace()[1].getMethodName(), str);
        return str;
    }
}
