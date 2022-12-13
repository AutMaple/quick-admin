package com.autmaple.oauth.controller;

import com.autmaple.oauth.mapper.PermissionMapper;
import com.autmaple.oauth.model.PermissionRoles;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 权限表 前端控制器
 * </p>
 *
 * @author AutMaple
 * @since 2022-12-13
 */
@RestController
@RequestMapping("/oauth/permission")
@RequiredArgsConstructor
public class PermissionController {
    private final PermissionMapper permissionMapper;

    @Operation(summary = "获取所有资源对应的角色信息")
    @GetMapping("/roles")
    public List<PermissionRoles> getPermissionRoles() {
        return permissionMapper.getPermissionRoles();
    }
}
