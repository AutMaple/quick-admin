package com.autmaple.oauth.service.impl;

import com.autmaple.oauth.entity.Permission;
import com.autmaple.oauth.mapper.PermissionMapper;
import com.autmaple.oauth.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author AutMaple
 * @since 2022-12-13
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

}
