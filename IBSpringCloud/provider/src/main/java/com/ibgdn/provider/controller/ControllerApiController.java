package com.ibgdn.provider.controller;

import com.ibgdn.api.IUserService;
import com.ibgdn.commons.model.User;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Open Feign 继承特性 Controller Api 演示类。
 */
public class ControllerApiController implements IUserService {
    /**
     * GetMapping 对应 provider controller 层的调用接口。
     * openfeignGet 方法名没有限制，返回值要和 provider 的接口返回值一致。
     * 通过 FeignClient 和 GetMapping 来确定具体调用哪个接口
     */
    @Override
    public String openFeignServiceGet() {
        return "Provider Controller providerGet. Name: ";
    }

    @Override
    public String openFeignServiceProviderGet(String name) {
        return "Provider Controller providerGet. Name: " + name;
    }

    @Override
    public User openFeignServiceProviderPost(User user) {
        return user;
    }

    @Override
    public User openFeignServiceProviderPut(User user) {
        return null;
    }

    @Override
    public void openFeignServiceProviderDelete(Integer id) {
        // Class: com.ibgdn.provider.controller.ProviderController, Method: providerDelete2, Id: 99
        System.out.println("Class: " + this.getClass().getName()
                + ", Method: " + Thread.currentThread().getStackTrace()[1].getMethodName()
                + ", Id: " + id);
    }

    @Override
    public void openFeignServiceProviderDeleteRequestHeader(String name) throws UnsupportedEncodingException {
        System.out.println("Class: " + this.getClass().getName()
                + ", Method: " + Thread.currentThread().getStackTrace()[1].getMethodName()
                + ", UserName: " + URLDecoder.decode(name, "UTF-8"));
    }
}
