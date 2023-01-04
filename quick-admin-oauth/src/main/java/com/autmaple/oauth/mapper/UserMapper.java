package com.autmaple.oauth.mapper;

import com.autmaple.oauth.configs.security.CustomUserDetails;
import com.autmaple.oauth.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author AutMaple
 * @since 2022-12-13
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    /**
     * 获取用户的角色信息
     *
     * @param username 用户名
     */
    CustomUserDetails getUserDetails(@Param("username") String username);
}
