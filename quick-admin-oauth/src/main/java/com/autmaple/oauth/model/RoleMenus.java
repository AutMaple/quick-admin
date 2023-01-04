package com.autmaple.oauth.model;

import com.autmaple.oauth.entity.Menu;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Schema(name = "RoleMenus", description = "角色对应的所拥有的菜单")
public class RoleMenus {
    private Long roleId;
    private String roleName;
    private List<Menu> menus;
}
