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
 * 角色权限关联表
 * </p>
 *
 * @author AutMaple
 * @since 2022-12-13
 */
@Getter
@Setter
@TableName("auth_role_permission")
@Schema(name = "RolePermission", description = "角色权限关联表")
public class RolePermission implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "角色 ID")
    @TableField("role_id")
    private Long roleId;

    @Schema(description = "权限 ID")
    @TableField("permission_id")
    private Long permissionId;
}
