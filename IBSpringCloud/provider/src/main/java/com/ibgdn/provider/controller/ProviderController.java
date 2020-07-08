package com.ibgdn.provider.controller;

import com.ibgdn.commons.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

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

    /**
     * RestTemplate Put 方法
     *
     * @param user 添加用户（key、value)
     */
    @PutMapping("/providerPutKeyValue")
    public void providerPut(User user) {
        // Class: com.ibgdn.provider.controller.ProviderController, Method: providerPut, User: User{id=99, username='userKeyValue', password='123456'}
        System.out.println("Class: " + this.getClass().getName()
                + ", Method: " + Thread.currentThread().getStackTrace()[1].getMethodName()
                + ", User: " + user);
    }

    /**
     * RestTemplate Put 方法
     *
     * @param user 添加用户（json）
     */
    @PutMapping("/providerPutJson")
    public void providerPut2(@RequestBody User user) {
        // Class: com.ibgdn.provider.controller.ProviderController, Method: providerPut2, User: User{id=999, username='userJson', password='123456'}
        System.out.println("Class: " + this.getClass().getName()
                + ", Method: " + Thread.currentThread().getStackTrace()[1].getMethodName()
                + ", User: " + user);
    }

    /**
     * RestTemplate Delete 方法
     */
    @DeleteMapping("/providerDeleteKeyValue")
    public void providerDelete(Integer id) {
        // Class: com.ibgdn.provider.controller.ProviderController, Method: providerDelete, Id: 98
        System.out.println("Class: " + this.getClass().getName()
                + ", Method: " + Thread.currentThread().getStackTrace()[1].getMethodName()
                + ", Id: " + id);
    }

    /**
     * RestTemplate Delete 方法
     */
    @DeleteMapping("/providerDeletePathVariable/{id}")
    public void providerDelete2(@PathVariable Integer id) {
        // Class: com.ibgdn.provider.controller.ProviderController, Method: providerDelete2, Id: 99
        System.out.println("Class: " + this.getClass().getName()
                + ", Method: " + Thread.currentThread().getStackTrace()[1].getMethodName()
                + ", Id: " + id);
    }

    /**
     * OpenFeign 服务 RequestHeader 测试接口
     *
     * @param name RequestHeader 绑定参数
     */
    @GetMapping("/openFeignRequestHeader")
    public void getUserByName(@RequestHeader String name) throws UnsupportedEncodingException {
        System.out.println("Class: " + this.getClass().getName()
                + ", Method: " + Thread.currentThread().getStackTrace()[1].getMethodName()
                + ", UserName: " + URLDecoder.decode(name, "UTF-8"));
    }


    /**
     * Resilience4j ReTry 接口
     * @return String 返回数据
     */
    @GetMapping("/ProviderResilience4jReTry")
    public String providerResilience4j() {
        String str = "Provider Controller. Port: " + port;
        System.out.println("Class: " + this.getClass().getName()
                + ", Method: " + Thread.currentThread().getStackTrace()[1].getMethodName()
                + ", Str: " + str);
        int i = 1 / 0;
        return str;
    }
}
