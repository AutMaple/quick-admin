package com.autmaple.oauth.mapstruct;

import com.autmaple.oauth.dto.UserDto;
import com.autmaple.oauth.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = ".", source = "user")
    UserDto uer2UserDto(User user);
}
