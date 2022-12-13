package com.autmaple.oauth.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Schema(name = "PermissionRoles", description = "一个权限对应的多个角色")
public class PermissionRoles {
    private String permission;
    private List<String> roles;
}
