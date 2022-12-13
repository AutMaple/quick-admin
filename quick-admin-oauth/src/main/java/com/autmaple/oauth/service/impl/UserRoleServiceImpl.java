package com.autmaple.oauth.service.impl;

import com.autmaple.oauth.entity.UserRole;
import com.autmaple.oauth.mapper.UserRoleMapper;
import com.autmaple.oauth.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色关联表 服务实现类
 * </p>
 *
 * @author AutMaple
 * @since 2022-12-13
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
