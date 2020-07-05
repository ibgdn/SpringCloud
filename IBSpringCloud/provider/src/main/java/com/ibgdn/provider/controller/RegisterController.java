package com.ibgdn.provider.controller;

import com.ibgdn.commons.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 注册登录测试接口
 */
@Controller
public class RegisterController {
    /**
     * 注册重定向
     *
     * @param user 用户信息
     * @return String 重定向地址
     */
    @PostMapping("/register")
    public String register(User user) {
        // 路径一定要填写绝对路径
        return "redirect:http://provider/loginPage?username=" + user.getUsername();
    }

    /**
     * 登录接口
     *
     * @param username 用户信息
     * @return String 登录返回信息
     */
    @GetMapping("/loginPage")
    @ResponseBody
    public String loginPage(String username) {
        return "loginPage:" + username;
    }
}
