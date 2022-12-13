package com.autmaple.oauth.service.impl;

import com.autmaple.oauth.entity.User;
import com.autmaple.oauth.mapper.UserMapper;
import com.autmaple.oauth.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author AutMaple
 * @since 2022-12-13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
