package com.autmaple.oauth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Objects;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author AutMaple
 * @since 2023-01-04
 */
@Getter
@Setter
@ToString
@TableName("auth_menu")
@Schema(name = "Menu", description = "菜单表")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "菜单名")
    @TableField("name")
    private String name;

    @Schema(description = "URL")
    @TableField("url")
    private String url;

    @Schema(description = "ICON")
    @TableField("icon")
    private String icon;

    @Schema(description = "父菜单 ID")
    @TableField("parent_id")
    private Long parentId;

    @Schema(description = "菜单层级")
    @TableField("level")
    private Integer level;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (getClass() != o.getClass())
            return false;
        Menu other = (Menu) o;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }
}
