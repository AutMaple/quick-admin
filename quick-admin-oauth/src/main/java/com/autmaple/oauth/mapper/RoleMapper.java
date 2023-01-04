package com.autmaple.oauth.mapper;

import com.autmaple.oauth.entity.Role;
import com.autmaple.oauth.model.RoleMenus;
import com.autmaple.oauth.model.RolePermissions;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
    RolePermissions rolePermissions(@Param("roleId") Long roleId);
    RoleMenus roleMenus(@Param("roleId") Long roleId);
}
