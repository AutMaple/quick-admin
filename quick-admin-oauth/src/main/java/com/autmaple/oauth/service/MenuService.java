package com.autmaple.oauth.service;

import com.autmaple.oauth.entity.Menu;
import com.autmaple.oauth.model.MenuNode;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author AutMaple
 * @since 2023-01-04
 */
public interface MenuService extends IService<Menu> {
    /**
     * 获取用户对应的菜单
     * @param username 用户名
     * @return 用户对应的菜单列表
     */
    List<MenuNode> userMenus(String username);
}
