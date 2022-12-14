package com.autmaple.oauth.controller;

import com.autmaple.oauth.configs.security.CustomUserDetails;
import com.autmaple.oauth.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author AutMaple
 * @since 2022-12-13
 */
@RestController
@RequestMapping("/oauth/user")
@RequiredArgsConstructor
public class UserController {
    private final UserMapper userMapper;

    @GetMapping("/authentication")
    public CustomUserDetails getUserAuthentication(@RequestParam String username) {
        return userMapper.getUserDetails(username);
    }

    @GetMapping("/addUser")
    public String addUser() {
        return "添加用户";
    }

    @GetMapping("/deleteUser")
    public String deleteUser() {
        return "删除用户";
    }

    @GetMapping("/getUser")
    public String getUser() {
        return "查询用户";
    }

    @GetMapping("/getUsers")
    public String getUsers() {
        return "查询全部用户";
    }

    @GetMapping("/updateUser")
    public String updateUser() {
        return "更新用户";
    }
}
