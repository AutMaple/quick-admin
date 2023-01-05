package com.autmaple.oauth.mapper;

import com.autmaple.oauth.entity.Menu;
import com.autmaple.oauth.entity.Role;
import com.autmaple.oauth.model.RoleMenus;
import com.autmaple.oauth.model.RolePermissions;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author AutMaple
 * @since 2022-12-13
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 获取用户所拥有的权限的详细信息
     *
     * @param roleId 角色 ID
     */
    RolePermissions rolePermissions(@Param("roleId") Long roleId);

    /**
     * 获取角色所拥有的菜单的详细情况
     *
     * @param roleId 角色 ID
     */
    RoleMenus roleMenus(@Param("roleId") Long roleId);

    /**
     * 获取用户所有用的角色的 ID
     *
     * @param userId 用户 ID
     * @return 用户角色 ID 集合
     */
    List<Long> getRoleIds(@Param("userId") Long userId);

    /**
     * 获取角色所拥有的菜单的 ID
     *
     * @param roleId 角色 ID
     */
    List<Long> getMenuIds(@Param("roleId") Long roleId);

    /**
     * 获取角色对应的菜单
     *
     * @param roleId 角色 ID
     */
    List<Menu> getMenus(@Param("roleId") Long roleId);
}
