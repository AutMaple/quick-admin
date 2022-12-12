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
 * 用户表
 * </p>
 *
 * @author AutMaple
 * @since 2022-12-12
 */
@Getter
@Setter
@TableName("auth_user")
@Schema(name = "User", description = "用户表")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "用户名")
    @TableField("username")
    private String username;

    @Schema(description = "密码")
    @TableField("password")
    private String password;

    @Schema(description = "邮箱")
    @TableField("email")
    private String email;

    @Schema(description = "性别：0 -> 男; 1-> 女; 2 -> 未知")
    @TableField("gender")
    private byte[] gender;
}
