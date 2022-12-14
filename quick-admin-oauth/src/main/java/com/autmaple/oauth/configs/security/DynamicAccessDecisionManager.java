package com.autmaple.oauth.configs.security;

import cn.hutool.core.collection.CollectionUtil;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * 动态权限决策管理器, {@link DynamicAccessDecisionManager} 类用于判断用户是否有其请求资源的访问权限
 */
public class DynamicAccessDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        if (CollectionUtil.isEmpty(configAttributes))
            return;
        for (ConfigAttribute config : configAttributes) {
            String needRole = config.getAttribute();
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                String curRole = authority.getAuthority();
                if (curRole.trim().equals(needRole.trim()))
                    return;
            }
        }
        throw new AccessDeniedException("没有访问当前资源的权限");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
