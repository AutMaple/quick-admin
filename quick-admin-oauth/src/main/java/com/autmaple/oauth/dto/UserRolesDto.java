package com.autmaple.oauth.dto;

import com.autmaple.oauth.model.RolePermissions;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 用户角色 dto
 */
@Getter
@Setter
public class UserRolesDto {
    private String username;
    private List<RolePermissions> roles;
}
