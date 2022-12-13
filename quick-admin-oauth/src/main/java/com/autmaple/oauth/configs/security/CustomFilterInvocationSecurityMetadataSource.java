package com.autmaple.oauth.configs.security;

import com.autmaple.oauth.mapper.PermissionMapper;
import com.autmaple.oauth.model.PermissionRoles;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource, InitializingBean {
    private final PermissionMapper permissionMapper;

    private Map<String, Collection<ConfigAttribute>> permissionToRoles = null;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        HttpServletRequest request = ((FilterInvocation) object).getRequest();
        for (String permission : permissionToRoles.keySet()) {
            if (new AntPathRequestMatcher(permission).matches(request)) {
                return permissionToRoles.get(permission);
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    /**
     * 将权限与角色的关系信息从数据库中加载到内存中
     */
    public void initPermissionToRoles() {
        if (this.permissionToRoles != null) {
            this.permissionToRoles.clear();
        }
        this.permissionToRoles = new HashMap<>();
        List<PermissionRoles> permissionRoles = permissionMapper.getPermissionRoles();
        for (PermissionRoles permissionRole : permissionRoles) {
            String permission = permissionRole.getPermission();
            List<String> roles = permissionRole.getRoles();
            List<SecurityConfig> configs = roles.stream()
                    .distinct()
                    .map(SecurityConfig::new)
                    .collect(Collectors.toList());
            Collection<ConfigAttribute> attributes = this.permissionToRoles.computeIfAbsent(permission, (key) -> new ArrayList<>());
            attributes.addAll(configs);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.initPermissionToRoles();
    }
}
