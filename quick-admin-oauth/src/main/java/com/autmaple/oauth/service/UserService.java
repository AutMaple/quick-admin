package com.autmaple.oauth.service;

import com.autmaple.oauth.configs.security.CustomUserDetails;
import com.autmaple.oauth.entity.User;
import com.autmaple.oauth.model.MenuNode;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author AutMaple
 * @since 2022-12-13
 */
public interface UserService extends IService<User> {
    /**
     * 获取用户登录凭证的详细信息
     *
     * @param username 用户名
     */
    CustomUserDetails getUserDetails(String username);

    /**
     * 更新用户
     */
    boolean updateUser(User user);

    /**
     * 获取用户的菜单信息
     *
     * @param username 用户名
     */
    List<MenuNode> userMenus(String username);
}
