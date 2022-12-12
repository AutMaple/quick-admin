package com.autmaple.oauth.mapper;

import com.autmaple.oauth.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author AutMaple
 * @since 2022-12-12
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

}
