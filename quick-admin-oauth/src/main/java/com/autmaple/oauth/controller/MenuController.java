package com.autmaple.oauth.controller;

import com.autmaple.oauth.model.MenuNode;
import com.autmaple.oauth.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author AutMaple
 * @since 2023-01-04
 */
@RestController
@RequestMapping("/oauth/menu")
@RequiredArgsConstructor
public class MenuController {
    private final RoleService roleService;


    @GetMapping("/all")
    public List<MenuNode> allMenus() {
        return roleService.allMenus();
    }
}
