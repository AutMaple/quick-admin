package com.autmaple.oauth.mapper;

import com.autmaple.oauth.dto.UserPermissionDto;
import com.autmaple.oauth.dto.UserRolesDto;
import com.autmaple.oauth.entity.Permission;
import com.autmaple.oauth.model.PermissionRoles;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author AutMaple
 * @since 2022-12-13
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {
    /**
     * 获取每个资源可被什么角色所操作
     */
    List<PermissionRoles> getPermissionRoles();

    UserPermissionDto userPermissions(@Param("username") String username);

    UserRolesDto userRoles(@Param("username") String username);
}
