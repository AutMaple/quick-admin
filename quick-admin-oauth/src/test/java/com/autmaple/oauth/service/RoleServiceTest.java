package com.autmaple.oauth.service;

import cn.hutool.json.JSONUtil;
import com.autmaple.oauth.model.MenuNode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class RoleServiceTest {
    @Autowired
    private RoleService roleService;

    @Test
    void userMenus() {
        List<MenuNode> userMenus = roleService.userMenus(2L);
        System.out.println(JSONUtil.toJsonStr(userMenus));
        List<MenuNode> irvinMenus = roleService.userMenus(1L);
        System.out.println(JSONUtil.toJsonStr(irvinMenus));
    }
}
