package com.autmaple.oauth.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class MenuNode {
    private Long id;
    private String name;
    private String url;
    private String icon;
    private Long parentId;
    private Integer level;
    private List<MenuNode> childMenus;

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
}
