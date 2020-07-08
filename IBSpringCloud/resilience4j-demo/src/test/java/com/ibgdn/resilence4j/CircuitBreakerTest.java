package com.ibgdn.resilence4j;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.vavr.CheckedFunction0;
import io.vavr.control.Try;
import org.junit.Test;

import java.time.Duration;

/**
 * Resilience4j CircuitBreaker（断路器） 测试类
 */
public class CircuitBreakerTest {

    /**
     * 正常执行的断路器测试方法
     */
    @Test
    public void successTest() {
        // 1. 获取 CircuitBreaker 实例（默认）
        CircuitBreakerRegistry defaultsRegister = CircuitBreakerRegistry.ofDefaults();

        // 2. 获取 CircuitBreaker 实例（自定义）
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                // 故障率阈值百分比，超过阈值，断路器就会自动打开
                .failureRateThreshold(50)
                // 断路器打开时间保持时间，到达设置时间之后，断路器会进入 half open 状态。
                .waitDurationInOpenState(Duration.ofMillis(1000))
                // 断路器进入 half open 状态，环形缓冲区大小
                .ringBufferSizeInHalfOpenState(2)
                // 有2条数据时测试缓冲区大小
                .ringBufferSizeInClosedState(2)
                .build();
        CircuitBreakerRegistry customRegister = CircuitBreakerRegistry.of(config);
        CircuitBreaker circuitBreaker = customRegister.circuitBreaker("CircuitBreakerTest");

        // CircuitBreaker 装饰函数
        CheckedFunction0<String> stringCheckedFunction0 =
                CircuitBreaker.decorateCheckedSupplier(circuitBreaker, () -> "Hello, Resilience4j");
        // 调用被装饰的函数
        Try<String> result = Try.of(stringCheckedFunction0)
                .map(value -> value + " Test.");
        System.out.println("Result: isSuccess(): " + result.isSuccess() + ", get(): " + result.get());
    }

    /**
     * 非正常执行的断路器测试方法
     */
    @Test
    public void failTest() {
        // 2. 获取 CircuitBreaker 实例（自定义）
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                // 故障率阈值百分比，超过阈值，断路器就会自动打开
                .failureRateThreshold(50)
                // 断路器打开时间保持时间，到达设置时间之后，断路器会进入 half open 状态。
                .waitDurationInOpenState(Duration.ofMillis(1000))
                // 断路器进入 half open 状态，环形缓冲区大小
                // 有2条数据时测试缓冲区大小
                .ringBufferSizeInClosedState(2)
                .build();
        CircuitBreakerRegistry customRegister = CircuitBreakerRegistry.of(config);
        CircuitBreaker circuitBreaker = customRegister.circuitBreaker("testCircuitBreaker");
        // 获取断路器状态
        System.out.println("CircuitBreaker Status: " + circuitBreaker.getState());
        circuitBreaker.onError(0, new RuntimeException());
        System.out.println("CircuitBreaker Status: " + circuitBreaker.getState());
        circuitBreaker.onError(0, new RuntimeException());
        System.out.println("CircuitBreaker Status: " + circuitBreaker.getState());

        System.out.println("CircuitBreaker（断路器）状态为打开状态时，后续代码无法正常执行！");
        // CircuitBreaker 装饰函数
/*
        CheckedFunction0<String> stringCheckedFunction0 =
                CircuitBreaker.decorateCheckedSupplier(circuitBreaker, () -> "Hello, Resilience4j");
        // 调用被装饰的函数
        Try<String> result = Try.of(stringCheckedFunction0)
                .map(value -> value + " Test.");
        System.out.println("Result: isSuccess(): " + result.isSuccess() + ", get(): " + result.get());
*/

        circuitBreaker.reset();
        System.out.println("CircuitBreaker（断路器）重置状态后，后续代码将正常执行！");
        // CircuitBreaker 装饰函数
        CheckedFunction0<String> stringCheckedFunction0 =
                CircuitBreaker.decorateCheckedSupplier(circuitBreaker, () -> "Hello, Resilience4j");
        // 调用被装饰的函数
        Try<String> result = Try.of(stringCheckedFunction0)
                .map(value -> value + " Test.");
        System.out.println("Result: isSuccess(): " + result.isSuccess() + ", get(): " + result.get());
    }
}
