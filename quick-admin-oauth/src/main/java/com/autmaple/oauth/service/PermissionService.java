package com.autmaple.oauth.service;

import com.autmaple.oauth.dto.UserPermissionDto;
import com.autmaple.oauth.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author AutMaple
 * @since 2022-12-13
 */
public interface PermissionService extends IService<Permission> {
    /**
     * 获取用户的权限
     * @param username 用户名
     */
    UserPermissionDto userPermission(String username);
}
