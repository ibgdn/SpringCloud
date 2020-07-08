package com.ibgdn.openfeign;

import com.ibgdn.api.IUserService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * OpenFeign 接口
 */
//@FeignClient(value = "provider", fallback = OpenFeignServiceFallBack.class)
// OpenFeignServiceFallBack 或者 OpenFeignServiceFallBackFactory
@FeignClient(value = "provider", fallbackFactory = OpenFeignServiceFallBackFactory.class)
public interface OpenFeignService extends IUserService {
    /**
     * GetMapping 对应 provider controller 层的调用接口。
     * openfeignGet 方法名没有限制，返回值要和 provider 的接口返回值一致。
     * 通过 FeignClient 和 GetMapping 来确定具体调用哪个接口
     */
/*
    @GetMapping("/providerGet")
    String openFeignServiceGet();

    @GetMapping("/providerGet")
    String openFeignServiceProviderGet(@RequestParam("name") String name);

    @PostMapping("/providerPostJson")
    User openFeignServiceProviderPost(@RequestBody User user);

    @PutMapping("/")
    User openFeignServiceProviderPut(@RequestBody User user);

    @DeleteMapping("/providerDeletePathVariable/{id}")
    void openFeignServiceProviderDelete(@PathVariable("id") Integer id);

    @GetMapping("/openFeignRequestHeader")
    void openFeignServiceProviderDeleteRequestHeader(@RequestHeader("name") String name);
*/
}
