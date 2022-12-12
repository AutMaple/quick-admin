package com.autmaple.oauth.service.impl;

import com.autmaple.oauth.entity.Role;
import com.autmaple.oauth.mapper.RoleMapper;
import com.autmaple.oauth.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author AutMaple
 * @since 2022-12-12
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
