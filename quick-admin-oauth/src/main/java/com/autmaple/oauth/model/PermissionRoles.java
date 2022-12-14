package com.autmaple.oauth.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 权限对应的角色，该实体类用于代表一个权限分配给多个角色
 */
@Getter
@Setter
@Schema(name = "PermissionRoles", description = "一个权限对应的多个角色")
public class PermissionRoles {
    private String permission;
    private List<String> roles;
}
