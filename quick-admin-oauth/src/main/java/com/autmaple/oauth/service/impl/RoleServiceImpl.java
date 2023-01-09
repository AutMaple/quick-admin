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

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        Map<Long, MenuNode> menuNodeMap = buildMenuTree(allMenus, allMenus);
        return menuNodeMap.values()
                .stream()
                .filter(menuNode -> menuNode.getParentId() == 1)
                .collect(Collectors.toList());
    }

    @Override
    public List<MenuNode> userMenus(Long userId) {
        // 1. 首先获取用户的角色
        List<Long> roleIds = roleMapper.getRoleIds(userId);

        // 2. 获取所有角色对应的菜单,并进行去重操作
        List<Menu> userMenus = roleIds
                .stream()
                .map(roleMapper::getMenus)
                .flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.toList());

        // 3. 获取项目所有的菜单项
        List<Menu> allMenus = menuMapper.selectList(null);

        // 4. 构建菜单树
        Map<Long, MenuNode> menuNodeMap = buildMenuTree(allMenus, userMenus);

        // 5. 挑选出一级菜单作为结果返回
        return menuNodeMap.values().stream()
                .filter(menuNode -> menuNode.getParentId() == -1)
                .collect(Collectors.toList());
    }

    /**
     * 根据项目中用户菜单和项目中所有的菜单构建出用户的菜单树
     *
     * @param allMenus  项目中所有的菜单项
     * @param userMenus 用户拥有的菜单项
     * @return 构建好的用户菜单树
     */
    private Map<Long, MenuNode> buildMenuTree(List<Menu> allMenus, List<Menu> userMenus) {
        Map<Long, Menu> allMenusMap = allMenus
                .stream()
                .collect(Collectors.toMap(Menu::getId, menu -> menu));

        Map<Long, MenuNode> menuNodeMap = new HashMap<>();
        for (Menu menu : userMenus) {
            Long parentId = menu.getParentId();
            if (parentId == -1) { // 一级菜单
                MenuNode menuNode = MenuNodeMapper.INSTANCE.menuToMenuNode(menu);
                menuNodeMap.put(menu.getId(), menuNode);
            } else {
                MenuNode parentNode = menuNodeMap.get(parentId);
                if (parentNode == null)
                    parentNode = findParentNode(menu, allMenusMap, menuNodeMap);
                MenuNode childNode = MenuNodeMapper.INSTANCE.menuToMenuNode(menu);
                parentNode.addChildMenu(childNode);
                menuNodeMap.put(childNode.getId(), childNode);
            }
        }
        return menuNodeMap;
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
                                    Map<Long, Menu> allMenuMap,
                                    Map<Long, MenuNode> menuNodeMap) {
        Long parentId = childMenu.getParentId();
        MenuNode parentMenuNode = menuNodeMap.get(parentId);
        // 1. 如果菜单是一级菜单则返回 null, 因为一级菜单没有父级菜单
        // 2. 如果父级菜单已经存在了，直接返回即可
        if (parentId == -1 || parentMenuNode != null)
            return parentMenuNode;

        // 3. 父级菜单不存在且父级菜单是非一级菜单, 则先递归的构建祖先节点
        Menu menu = allMenuMap.get(parentId);
        MenuNode ancestorNode = findParentNode(menu, allMenuMap, menuNodeMap);
        parentMenuNode = MenuNodeMapper.INSTANCE.menuToMenuNode(menu);

        // 如果 ancestorNode 为 null，说明当前节点为一级菜单, 直接放入 menuNodeMap 即可
        if (ancestorNode != null && !ancestorNode.haveChildMenu(parentMenuNode))
            ancestorNode.addChildMenu(parentMenuNode);

        menuNodeMap.put(parentId, parentMenuNode);
        return parentMenuNode;
    }
}
