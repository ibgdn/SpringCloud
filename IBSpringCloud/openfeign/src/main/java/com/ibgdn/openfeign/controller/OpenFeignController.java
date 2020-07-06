package com.ibgdn.openfeign.controller;

import com.ibgdn.commons.model.User;
import com.ibgdn.openfeign.OpenFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * OpenFeign Controller
 */
@RestController
public class OpenFeignController {
    @Autowired
    OpenFeignService openFeignService;

    /**
     * OpenFeign Controller 层 Get 接口
     *
     * @return 调用 service 的接口
     */
    @GetMapping("/OpenFeignControllerGet")
    public String openFeignControllerGet() {
        return openFeignService.openFeignServiceGet();
    }

    /**
     * 除 Get 方法之外的方法测试
     *
     * @return String 调用 Post 方法返回的字符串
     * @throws UnsupportedEncodingException
     *
     * 页面展示如下：
     * openFeignServiceGet: Provider Controller providerGet. Name: null
     * openFeignServiceProviderGet: Provider Controller providerGet. Name: 控制层接口
     * openFeignServiceProviderPost: User{id=0, username='Open Feign', password='123456'}
     *
     * Provider 控制台输出内容如下：
     * Class: com.ibgdn.provider.controller.ProviderController, Method: providerDelete2, Id: 1
     * Class: com.ibgdn.provider.controller.ProviderController, Method: getUserByName, UserName: 控制层接口删除方法
     */
    @GetMapping("/OpenFeignControllerOthers")
    public String openFeignControllerOthers() throws UnsupportedEncodingException {
        StringBuilder stringBuilder = new StringBuilder();

        String openFeignServiceGet = openFeignService.openFeignServiceGet();
        stringBuilder.append("openFeignServiceGet: ").append(openFeignServiceGet).append("\n");

        String openFeignServiceProviderGet = openFeignService.openFeignServiceProviderGet("控制层接口");
        stringBuilder.append("openFeignServiceProviderGet: ").append(openFeignServiceProviderGet).append("\n");

        User user = new User();
        user.setId(0);
        user.setUsername("Open Feign");
        user.setPassword("123456");
        User openFeignServiceProviderPost = openFeignService.openFeignServiceProviderPost(user);
        stringBuilder.append("openFeignServiceProviderPost: ").append(openFeignServiceProviderPost).append("\n");

        openFeignService.openFeignServiceProviderDelete(1);

        openFeignService.openFeignServiceProviderDeleteRequestHeader(URLEncoder.encode("控制层接口删除方法", "UTF-8"));

        return stringBuilder.toString();
    }
}
