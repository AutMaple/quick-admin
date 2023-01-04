package com.autmaple.oauth.service.impl;

import com.autmaple.oauth.configs.security.CustomUserDetails;
import com.autmaple.oauth.dto.UserRolesDto;
import com.autmaple.oauth.entity.User;
import com.autmaple.oauth.mapper.PermissionMapper;
import com.autmaple.oauth.mapper.UserMapper;
import com.autmaple.oauth.model.MenuNode;
import com.autmaple.oauth.model.RolePermissions;
import com.autmaple.oauth.service.UserService;
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
    private final PermissionMapper permissionMapper;

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

    @Override
    public List<MenuNode> userMenus(String username) {
        // 1. 查出用户的角色
        UserRolesDto userRoles = permissionMapper.userRoles(username);
        List<RolePermissions> roles = userRoles.getRoles();
        // 2. 查出角色对应的所拥有的菜单
        // 2. 查出菜单对应的父级菜单
        // 3. 将菜单进行整合
        return null;
    }


}
