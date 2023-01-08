package com.autmaple.oauth.service.impl;

import com.autmaple.oauth.entity.Menu;
import com.autmaple.oauth.entity.Role;
import com.autmaple.oauth.mapper.MenuMapper;
import com.autmaple.oauth.mapper.RoleMapper;
import com.autmaple.oauth.mapstruct.MenuNodeMapper;
import com.autmaple.oauth.model.MenuNode;
import com.autmaple.oauth.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
    private final MenuMapper menuMapper;

    @Override
    public List<MenuNode> allMenus() {
        List<Menu> allMenus = menuMapper.selectList(null);
        Map<Integer, HashSet<Menu>> allMenuMap = new HashMap<>();
        for (Menu menu : allMenus) {
            HashSet<Menu> menuSet = allMenuMap.computeIfAbsent(menu.getLevel(), (key) -> new HashSet<>());
            menuSet.add(menu);
        }

        Map<Long, MenuNode> menuNodeMap = new HashMap<>();
        for (Menu menu : allMenus) {
            Long parentId = menu.getParentId();
            if (parentId == -1) { // 一级菜单
                MenuNode menuNode = MenuNodeMapper.INSTANCE.menuToMenuNode(menu);
                menuNodeMap.put(menu.getId(), menuNode);
            } else {
                MenuNode parentNode = menuNodeMap.computeIfAbsent(parentId,
                        (parentKey) -> findParentNode(menu, allMenuMap, menuNodeMap));
                MenuNode childNode = MenuNodeMapper.INSTANCE.menuToMenuNode(menu);
                parentNode.addChildMenu(childNode);
                menuNodeMap.put(childNode.getId(), childNode);
            }
        }

        List<MenuNode> result = new ArrayList<>();
        for (Menu menu : allMenuMap.getOrDefault(1, new HashSet<>())) { // 获取顶级节点
            MenuNode menuNode = menuNodeMap.get(menu.getId());
            if (menuNode != null)
                result.add(menuNode);
        }
        return result;
    }

    @Override
    public List<MenuNode> userMenus(Long userId) {
        // 1. 首先获取用户的角色
        List<Long> roleIds = roleMapper.getRoleIds(userId);

        // 2. 获取角色对应的菜单,并进行去重操作
        Set<Menu> rolesMenuSet = new HashSet<>();
        for (Long roleId : roleIds) {
            List<Menu> roleMenus = roleMapper.getMenus(roleId);
            rolesMenuSet.addAll(roleMenus);
        }
        // 3. 获取项目所有的菜单项, 并将菜单按照层级分类, 方便后续构建菜单
        List<Menu> allMenus = menuMapper.selectList(null);
        Map<Integer, HashSet<Menu>> allMenuMap = new HashMap<>();
        for (Menu menu : allMenus) {
            HashSet<Menu> menuSet = allMenuMap.computeIfAbsent(menu.getLevel(), (key) -> new HashSet<>());
            menuSet.add(menu);
        }

        // 4. 找出各菜单的祖先结点，从而构建出菜单树
        // key: 菜单 ID
        Map<Long, MenuNode> menuNodeMap = new HashMap<>();
        for (Menu menu : rolesMenuSet) {
            Long parentId = menu.getParentId();
            if (parentId == -1) { // 一级菜单
                MenuNode menuNode = MenuNodeMapper.INSTANCE.menuToMenuNode(menu);
                menuNodeMap.put(menu.getId(), menuNode);
            } else {
                MenuNode parentNode = menuNodeMap.computeIfAbsent(parentId,
                        (parentKey) -> findParentNode(menu, allMenuMap, menuNodeMap));
                MenuNode childNode = MenuNodeMapper.INSTANCE.menuToMenuNode(menu);
                parentNode.addChildMenu(childNode);
                menuNodeMap.put(childNode.getId(), childNode);
            }
        }

        // 5. 将一级菜单放入到集合中作为结果返回
        List<MenuNode> result = new ArrayList<>();
        for (Menu menu : allMenuMap.getOrDefault(1, new HashSet<>())) { // 获取顶级节点
            MenuNode menuNode = menuNodeMap.get(menu.getId());
            if (menuNode != null)
                result.add(menuNode);
        }
        return result;
    }

    /**
     * 获取菜单父类节点，并且一层层的将各级菜单连接起来
     *
     * @param childMenu   子菜单
     * @param allMenuMap  项目中所有的菜单
     * @param menuNodeMap 层级菜单
     * @return 父菜单
     */
    private MenuNode findParentNode(Menu childMenu,
                                    Map<Integer, HashSet<Menu>> allMenuMap,
                                    Map<Long, MenuNode> menuNodeMap) {
        Long parentId = childMenu.getParentId();
        Integer parentLevel = childMenu.getLevel() - 1;
        MenuNode parentMenuNode = menuNodeMap.get(parentId);

        // 如果父级菜单已经存在了，直接返回即可
        if (parentMenuNode != null)
            return parentMenuNode;

        // 父级菜单不存在且父级菜单是一级菜单，则创建一级菜单并返回
        if (parentLevel == 1) {
            for (Menu menu : allMenuMap.get(parentLevel)) {
                if (Objects.equals(menu.getId(), parentId)) {
                    parentMenuNode = MenuNodeMapper.INSTANCE.menuToMenuNode(menu);
                    menuNodeMap.put(parentId, parentMenuNode);
                    return parentMenuNode;
                }
            }
        }

        // 父级菜单不存在且父级菜单是非一级菜单, 则先递归的构建祖先节点
        for (Menu menu : allMenuMap.get(parentLevel)) {
            if (Objects.equals(menu.getId(), parentId)) {
                MenuNode ancestorNode = findParentNode(menu, allMenuMap, menuNodeMap);
                parentMenuNode = MenuNodeMapper.INSTANCE.menuToMenuNode(menu);
                if (!isChildMenu(parentMenuNode, ancestorNode))
                    ancestorNode.addChildMenu(parentMenuNode);
                menuNodeMap.put(parentId, parentMenuNode);
            }
        }
        return parentMenuNode;
    }

    /**
     * 判断 childMenu 是否是 parentMenu 的子菜单
     *
     * @param childMenu  子菜单
     * @param parentMenu 父菜单
     * @return 是则返回 true，否则返回 false
     */
    private boolean isChildMenu(MenuNode childMenu, MenuNode parentMenu) {
        if (parentMenu == null || childMenu == null)
            return false;
        List<MenuNode> menus = parentMenu.getChildMenus();
        for (MenuNode menu : menus) {
            if (Objects.equals(menu.getId(), childMenu.getId()))
                return true;
        }
        return false;
    }
}
