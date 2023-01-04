package com.autmaple.oauth.service.impl;

import com.autmaple.oauth.dto.UserRolesDto;
import com.autmaple.oauth.entity.Menu;
import com.autmaple.oauth.mapper.MenuMapper;
import com.autmaple.oauth.mapper.PermissionMapper;
import com.autmaple.oauth.mapper.RoleMapper;
import com.autmaple.oauth.mapper.UserMapper;
import com.autmaple.oauth.model.MenuNode;
import com.autmaple.oauth.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author AutMaple
 * @since 2023-01-04
 */
@Service
@RequiredArgsConstructor
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;
    private final PermissionMapper permissionMapper;

    @Override
    public List<MenuNode> userMenus(String username) {
        return null;
    }
}
