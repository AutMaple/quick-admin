package com.autmaple.oauth.mapper;


import cn.hutool.json.JSONUtil;
import com.autmaple.oauth.model.RoleMenus;
import com.autmaple.oauth.model.RolePermissions;
import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

@MybatisPlusTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RoleMapperTest {
    @Autowired
    private RoleMapper roleMapper;

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
}
