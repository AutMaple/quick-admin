package com.autmaple.oauth.mapper;

import com.autmaple.oauth.entity.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户角色关联表 Mapper 接口
 * </p>
 *
 * @author AutMaple
 * @since 2022-12-13
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {

}
