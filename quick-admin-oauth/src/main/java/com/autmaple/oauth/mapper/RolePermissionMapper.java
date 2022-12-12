package com.autmaple.oauth.mapper;

import com.autmaple.oauth.entity.RolePermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 角色权限关联表 Mapper 接口
 * </p>
 *
 * @author AutMaple
 * @since 2022-12-12
 */
@Mapper
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

}
