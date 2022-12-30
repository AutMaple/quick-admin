package com.autmaple.oauth.service.impl;

import com.autmaple.oauth.configs.security.CustomUserDetails;
import com.autmaple.oauth.entity.User;
import com.autmaple.oauth.mapper.UserMapper;
import com.autmaple.oauth.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author AutMaple
 * @since 2022-12-13
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserMapper userMapper;

    @Override
    public CustomUserDetails getUserDetails(String username) {
        return userMapper.getUserDetails(username);
    }

    @Override
    public boolean updateUser(User user) {
        String currentUsername  = SecurityContextHolder.getContext().getAuthentication().getName();
        Map<String, Object> wrapper = new HashMap<>();
        wrapper.put("username", currentUsername);
        List<User> users = userMapper.selectByMap(wrapper);
        return true;
    }


}
