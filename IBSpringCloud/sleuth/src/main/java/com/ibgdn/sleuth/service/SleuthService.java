package com.ibgdn.sleuth.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
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

    /**
     * 每10秒调用一次定时任务
     * 2020-07-12 23:21:10.002  INFO [sleuth,2f307268e8c6e22d,2f307268e8c6e22d,true] 23492 --- [   scheduling-1] com.ibgdn.sleuth.service.SleuthService   : Class: [com.ibgdn.sleuth.service.SleuthService], Method: [sleuthSchedule], Scheduled Start.
     * 2020-07-12 23:21:10.002  INFO [sleuth,2f307268e8c6e22d,2f307268e8c6e22d,true] 23492 --- [   scheduling-1] com.ibgdn.sleuth.service.SleuthService   : Class: [com.ibgdn.sleuth.service.SleuthService], Method: [sleuthService], Info: [sleuthService].
     * 2020-07-12 23:21:10.003  INFO [sleuth,2f307268e8c6e22d,2f307268e8c6e22d,true] 23492 --- [   scheduling-1] com.ibgdn.sleuth.service.SleuthService   : Class: [com.ibgdn.sleuth.service.SleuthService], Method: [sleuthSchedule], Scheduled End.
     */
    @Scheduled(cron = "0/10 * * * * ?")
    public void sleuthSchedule() {
        logger.info("Class: [{}], Method: [{}], Scheduled Start.",
                this.getClass().getName(), Thread.currentThread().getStackTrace()[1].getMethodName());
        sleuthService();
        logger.info("Class: [{}], Method: [{}], Scheduled End.",
                this.getClass().getName(), Thread.currentThread().getStackTrace()[1].getMethodName());
    }
}
