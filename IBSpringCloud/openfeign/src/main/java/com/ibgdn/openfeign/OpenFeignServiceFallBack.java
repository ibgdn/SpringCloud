package com.ibgdn.openfeign;

import com.ibgdn.commons.model.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;

/**
 * Hystrix 容错、服务降级
 */
@Component
// 实现接口 OpenFeignService 及 IUserService 自带相同接口，所以需要重新定义请求地址，防止请求地址重复
@RequestMapping("/openFeignFallBack")
public class OpenFeignServiceFallBack implements OpenFeignService {
    /**
     * GetMapping 对应 provider controller 层的调用接口。
     * openfeignGet 方法名没有限制，返回值要和 provider 的接口返回值一致。
     * 通过 FeignClient 和 GetMapping 来确定具体调用哪个接口
     */
    @Override
    public String openFeignServiceGet() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Class: ").append(this.getClass().getName())
                .append(", Method: ").append(Thread.currentThread().getStackTrace()[1].getMethodName());
        return stringBuilder.toString();
    }

    @Override
    public String openFeignServiceProviderGet(String name) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Class: ").append(this.getClass().getName())
                .append(", Method: ").append(Thread.currentThread().getStackTrace()[1].getMethodName());
        return stringBuilder.toString();
    }

    @Override
    public User openFeignServiceProviderPost(User user) {
        return null;
    }

    @Override
    public User openFeignServiceProviderPut(User user) {
        return null;
    }

    @Override
    public void openFeignServiceProviderDelete(Integer id) {

    }

    @Override
    public void openFeignServiceProviderDeleteRequestHeader(String name) throws UnsupportedEncodingException {

    }
}
