package com.autmaple.oauth.service;

import com.autmaple.oauth.entity.Role;
import com.autmaple.oauth.model.MenuNode;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author AutMaple
 * @since 2022-12-13
 */
public interface RoleService extends IService<Role> {
    /**
     * 获取用户全部的菜单
     *
     * @param userId 用户 ID
     */
    List<MenuNode> userMenus(Long userId);
}
