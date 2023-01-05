package com.autmaple.oauth.model;

import com.autmaple.oauth.entity.Menu;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 角色所拥有的菜单的详细信息
 */
@Getter
@Setter
@Schema(name = "RoleMenus", description = "角色所拥有的菜单的详细信息")
public class RoleMenus {
    private Long roleId;
    private String roleName;
    private List<Menu> menus;
}
