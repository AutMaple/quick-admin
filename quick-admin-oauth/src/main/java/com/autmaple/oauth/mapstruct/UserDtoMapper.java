package com.autmaple.oauth.mapstruct;

import com.autmaple.oauth.dto.UserDto;
import com.autmaple.oauth.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserDtoMapper {
    UserDtoMapper INSTANCE = Mappers.getMapper(UserDtoMapper.class);

    UserDto uer2UserDto(User user);
}
