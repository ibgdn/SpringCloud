package com.ibgdn.openfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * OpenFeign 接口
 */
@FeignClient("provider")
public interface OpenFeignService {
    /**
     * GetMapping 对应 provider controller 层的调用接口。
     * openfeignGet 方法名没有限制，返回值要和 provider 的接口返回值一致。
     * 通过 FeignClient 和 GetMapping 来确定具体调用哪个接口
     */
    @GetMapping("/providerGet")
    String openFeignServiceGet();
}
