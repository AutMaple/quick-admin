package com.autmaple.oauth.mapper;

import com.autmaple.oauth.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author AutMaple
 * @since 2022-12-12
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
