package com.ibgdn.api;

import com.ibgdn.commons.model.User;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

/**
 * Open Feign 请求接口抽象类
 */
@RestController
public interface IUserService {
    /**
     * GetMapping 对应 provider controller 层的调用接口。
     * openfeignGet 方法名没有限制，返回值要和 provider 的接口返回值一致。
     * 通过 FeignClient 和 GetMapping 来确定具体调用哪个接口
     */
    @GetMapping("/provider")
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
    void openFeignServiceProviderDeleteRequestHeader(@RequestHeader("name") String name) throws UnsupportedEncodingException;
}
