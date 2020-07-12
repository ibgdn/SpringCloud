package com.ibgdn.sleuth.controller;

import com.ibgdn.sleuth.service.SleuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 链路追踪系统 Sleuth
 */
@RestController
public class SleuthController {

    private static final Logger logger = LoggerFactory.getLogger(SleuthController.class);

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    SleuthService sleuthService;

    /**
     * Sleuth 简单调用
     *
     * @return String 返回数据
     */
    @GetMapping("/sleuth")
    public String sleuth() {
        logger.info("Spring Cloud Sleuth Controller.");
        return "Spring Cloud Sleuth Controller.";
    }

    /**
     * 链路追踪调用接口
     *
     * @return String 返回数据
     * @throws InterruptedException
     * 控制台输出：
     * <p>
     * 2020-07-12 22:58:22.664  INFO [sleuth,9cfd8137aef9dceb,9cfd8137aef9dceb,true] 9500 --- [nio-1220-exec-1] c.i.sleuth.controller.SleuthController   : Class: [com.ibgdn.sleuth.controller.SleuthController], Method: [sleuthRestTemplate], Info: [sleuthRestTemplate].
     * 2020-07-12 22:58:23.295  INFO [sleuth,9cfd8137aef9dceb,3676bae1e4688d35,true] 9500 --- [nio-1220-exec-2] c.i.sleuth.controller.SleuthController   : Class: [com.ibgdn.sleuth.controller.SleuthController], Method: [sleuthReturn], Info: [sleuthReturn].
     */
    @GetMapping("/sleuthRestTemplate")
    public String sleuthRestTemplate() throws InterruptedException {
        String str = "sleuthRestTemplate";
        logger.info("Class: [{}], Method: [{}], Info: [{}].",
                this.getClass().getName(), Thread.currentThread().getStackTrace()[1].getMethodName(), str);
        Thread.sleep(500);
        return restTemplate.getForObject("http://localhost:1220/sleuthReturn", String.class);
    }

    /**
     * 链路追踪被调用接口
     *
     * @return String 返回数据
     */
    @GetMapping("/sleuthReturn")
    public String sleuthReturn() {
        String str = "sleuthReturn";
        logger.info("Class: [{}], Method: [{}], Info: [{}].",
                this.getClass().getName(), Thread.currentThread().getStackTrace()[1].getMethodName(), str);
        return str;
    }

    /**
     * 异步任务调用接口
     *
     * @return String 返回数据
     */
    //2020-07-12 23:11:53.347  INFO [sleuth,535e9daed9508290,535e9daed9508290,true] 11040 --- [nio-1220-exec-2] c.i.sleuth.controller.SleuthController   : Class: [com.ibgdn.sleuth.controller.SleuthController], Method: [sleuthAsync], Info: [sleuthAsync].
    //2020-07-12 23:11:53.388  INFO [sleuth,535e9daed9508290,dad7d837f63d4e8e,true] 11040 --- [         task-1] com.ibgdn.sleuth.service.SleuthService   : Class: [com.ibgdn.sleuth.service.SleuthService], Method: [sleuthService], Info: [sleuthService].
    @GetMapping("/sleuthAsync")
    public String sleuthAsync() {
        String str = "sleuthAsync";
        logger.info("Class: [{}], Method: [{}], Info: [{}].",
                this.getClass().getName(), Thread.currentThread().getStackTrace()[1].getMethodName(), str);
        return sleuthService.sleuthService();
    }
}
