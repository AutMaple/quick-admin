package com.autmaple.oauth.dto;

import com.autmaple.oauth.entity.Permission;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 用户权限 dto
 */
@Getter
@Setter
public class UserPermissionDto {
    private String username;
    List<Permission> permissions;
}
