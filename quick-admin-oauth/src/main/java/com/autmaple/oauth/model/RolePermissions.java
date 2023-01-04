package com.autmaple.oauth.model;

import com.autmaple.oauth.entity.Permission;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 角色权限实体类，该类用于代表角色所拥有的权限
 */
@Getter
@Setter
@ToString
public class RolePermissions {
    private Long roleId;
    private String role;
    private List<Permission> permissions;
}
