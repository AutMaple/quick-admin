package com.autmaple.oauth.controller;

import com.autmaple.oauth.configs.security.CustomUserDetails;
import com.autmaple.oauth.dto.UserDto;
import com.autmaple.oauth.entity.User;
import com.autmaple.oauth.mapstruct.UserDtoMapper;
import com.autmaple.oauth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author AutMaple
 * @since 2022-12-13
 */
@RestController
@RequestMapping(value = "/oauth/user", produces = {"application/json;charset=UTF-8"})
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/authentication")
    public CustomUserDetails getUserAuthentication(@RequestParam String username) {
        return userService.getUserDetails(username);
    }

    @PostMapping("/addUser")
    public String addUser(@RequestBody User user) {
        user.setId(null);
        boolean success = userService.save(user);
        return success ? "添加用户成功" : "添加用户失败";
    }

    @PostMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable Long id) {
        boolean flag = userService.removeById(id);
        return flag ? "删除用户成功" : "删除用户失败";
    }

    @GetMapping("/getUser/{id}")
    public UserDto getUser(@PathVariable Long id) {
        User user = userService.getById(id);
        return UserDtoMapper.INSTANCE.uer2UserDto(user);
    }

    @PostMapping("/updateUser")
    public String updateUser(@RequestBody User user) {
        boolean success = userService.updateById(user);
        return success ? "更新用户成功" : "更新用户失败";
    }

    @GetMapping("/menus")
    public void userMenus(@RequestParam String username){

    }
}
