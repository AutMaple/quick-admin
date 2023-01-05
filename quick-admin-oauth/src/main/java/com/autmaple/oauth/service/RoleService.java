package com.autmaple.oauth.service;

import com.autmaple.oauth.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author AutMaple
 * @since 2022-12-13
 */
public interface RoleService extends IService<Role> {
    void userMenus(Long userId );
}
