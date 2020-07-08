package com.ibgdn.resilence4j;

import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import org.junit.Test;

import java.time.Duration;

/**
 * Resilience4j ReTry（请求重试） 测试类
 */
public class ReTryTest {
    @Test
    public void successTest() {
        RetryConfig retryConfig = RetryConfig.custom()
                // 最大重试次数
                .maxAttempts(5)
                // 重试间隔时间
                .waitDuration(Duration.ofMillis(2000))
                // 特定异常重试
                .retryExceptions(RuntimeException.class)
                .build();
        Retry retry = Retry.of("ReTryTest", retryConfig);
        Retry.decorateRunnable(retry, new Runnable() {
            /**
             * 开启重试功能之后，如果 run 方法执行时，抛出了指定的异常，会自动执行重试操作。
             * 重试次数要大于异常抛出次数，否则会因为异常抛出，程序运行停止。
             */
            int count = 0;

            @Override
            public void run() {
                if (count++ < 3) {
                    System.out.println("异常执行，Count: " + count);
                    throw new RuntimeException();
                }
                System.out.println("ReTry 请求成功，停止抛出异常！");
                System.out.println("Count: " + count);
            }
        }).run();
    }
}
