package com.autmaple.oauth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author AutMaple
 * @since 2022-12-12
 */
@Getter
@Setter
@TableName("auth_permission")
@Schema(name = "Permission", description = "权限表")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "权限")
    @TableField("permission")
    private String permission;

    @Schema(description = "描述")
    @TableField("description")
    private String description;
}
