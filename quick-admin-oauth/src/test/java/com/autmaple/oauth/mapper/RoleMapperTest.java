package com.autmaple.oauth.mapper;


import cn.hutool.json.JSONUtil;
import com.autmaple.oauth.entity.Menu;
import com.autmaple.oauth.model.MenuNode;
import com.autmaple.oauth.model.RoleMenus;
import com.autmaple.oauth.model.RolePermissions;
import com.autmaple.oauth.service.RoleServiceTest;
import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.List;

@MybatisPlusTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RoleMapperTest {
    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleServiceTest roleService;

    @Test
    void rolePermission() {
        RolePermissions rolePermissions = roleMapper.rolePermissions(2L);
        String str = JSONUtil.toJsonStr(rolePermissions);
        System.out.println(str);
    }

    @Test
    void roleMenus() {
        RoleMenus roleMenus = roleMapper.roleMenus(1L);
        String str = JSONUtil.toJsonStr(roleMenus);
        System.out.println(str);
    }

    @Test
    void getRoleIds() {
        List<Long> roleIds = roleMapper.getRoleIds(1L);
        System.out.println(roleIds);
    }

    @Test
    void getMenuIds() {
        List<Long> menuIds = roleMapper.getMenuIds(1L);
        System.out.println(menuIds);
    }

    @Test
    void getMenus() {
        List<Menu> menus = roleMapper.getMenus(1L);
        System.out.println(menus);
    }
}
