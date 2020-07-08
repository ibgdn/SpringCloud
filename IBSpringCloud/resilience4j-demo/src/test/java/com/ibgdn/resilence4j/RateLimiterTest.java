package com.ibgdn.resilence4j;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.vavr.CheckedRunnable;
import io.vavr.control.Try;
import org.junit.Test;

import java.time.Duration;
import java.util.Date;

/**
 * Resilience4j RateLimiter（限流） 测试类
 */
public class RateLimiterTest {
    @Test
    public void successTest() {
        RateLimiterConfig rateLimiterConfig = RateLimiterConfig.custom()
                // 阈值刷新时间
                .limitRefreshPeriod(Duration.ofMillis(2000))
                // 阈值刷新频次（每秒执行请求的次数）
                .limitForPeriod(2)
                // 限流之后的冷却时间
                .timeoutDuration(Duration.ofMillis(3000))
                .build();
        RateLimiter rateLimit = RateLimiter.of("RateLimitTest", rateLimiterConfig);

        CheckedRunnable checkedRunnable = RateLimiter.decorateCheckedRunnable(rateLimit, () -> {
            System.out.println("CheckedRunnable: " + new Date());
        });

        // 线性执行4次，如果失败执行 onFailure() 方法。
        Try.run(checkedRunnable)
                .andThenTry(checkedRunnable)
                .andThenTry(checkedRunnable)
                .andThenTry(checkedRunnable)
                .andThenTry(checkedRunnable)
                .onFailure(t -> System.out.println(t.getMessage()));
    }
}
