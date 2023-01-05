package com.autmaple.oauth.service.impl;

import com.autmaple.oauth.entity.Menu;
import com.autmaple.oauth.entity.Role;
import com.autmaple.oauth.mapper.RoleMapper;
import com.autmaple.oauth.model.MenuNode;
import com.autmaple.oauth.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author AutMaple
 * @since 2022-12-13
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    private final RoleMapper roleMapper;

    @Override
    public void userMenus(Long userId) {
        // 1. 首先获取用户的角色
        List<Long> roleIds = roleMapper.getRoleIds(userId);
        // 2. 获取角色对应的菜单
        // key: 菜单 level | value: menu 去重
        Map<Integer, HashSet<Menu>> menuMap = new HashMap<>();
        for (Long roleId : roleIds) {
            List<Menu> menus = roleMapper.getMenus(roleId);
            for (Menu menu : menus) {
                HashSet<Menu> menuSet = menuMap.computeIfAbsent(menu.getLevel(), (key) -> new HashSet<>());
                menuSet.add(menu);
            }
        }
        // key: 菜单 ID
        Map<Long, MenuNode> menuNodeMap = new HashMap<>();
        for (Map.Entry<Integer, HashSet<Menu>> entry : menuMap.entrySet()) {
            for (Menu menu : entry.getValue()) {
                Long parentId = menu.getParentId();
                MenuNode menuNode = new MenuNode();
                if (parentId == -1)
                    menuNodeMap.put(menu.getId(), menuNode);
            }
        }
    }
}
