package com.ibgdn.provider.controller;

import com.ibgdn.commons.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务注册接口
 */
@RestController
public class ProviderController {
    // 注入端口号。集群启动多实例时，通过端口号做区分。
    @Value("${server.port}")
    Integer port;

    @GetMapping("/provider")
    public String provider() {
        return "Provider Controller. Port: " + port;
    }

    /**
     * RestTemplate Get 方法
     *
     * @param name 传入参数
     * @return String 返回字符串
     */
    @GetMapping("/providerGet")
    public String providerGet(String name) {
        return "Provider Controller providerGet. Name: " + name;
    }

    /**
     * RestTemplate Post 方法
     *
     * @param user 添加用户（key、value)
     * @return user 添加用户
     */
    @PostMapping("/providerPostKeyValue")
    public User providerPost(User user) {
        return user;
    }

    /**
     * RestTemplate Post 方法
     *
     * @param user 添加用户（json）
     * @return user 添加用户
     */
    @PostMapping("/providerPostJson")
    public User providerPost2(@RequestBody User user) {
        return user;
    }
}
