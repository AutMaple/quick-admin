package com.autmaple.oauth.model;

import com.autmaple.oauth.entity.Permission;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 角色权限实体类，该类用于代表角色所拥有的权限
 */
@Getter
@Setter
public class RolePermissions {
    private String role;
    private List<Permission> permissions;
}
