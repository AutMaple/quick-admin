package com.autmaple.oauth.model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
public class MenuNode {
    private Long id;
    private String name;
    private String url;
    private String icon;
    private Long parentId;
    private Integer level;
    private Set<MenuNode> childMenus;

    public MenuNode() {
        this.childMenus = new HashSet<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (getClass() != o.getClass())
            return false;
        MenuNode other = (MenuNode) o;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    public void addChildMenu(MenuNode menuNode) {
        this.childMenus.add(menuNode);
    }


    /**
     * 判断 childMenu 是否是当前菜单的直接子菜单
     *
     * @param childMenu 子菜单
     * @return 是则返回 true，否则返回 false
     */
    public boolean haveChildMenu(MenuNode childMenu) {
        return this.childMenus.contains(childMenu);
    }
}
