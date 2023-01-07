package com.autmaple.oauth.mapstruct;

import com.autmaple.oauth.entity.Menu;
import com.autmaple.oauth.model.MenuNode;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MenuNodeMapper {
    MenuNodeMapper INSTANCE = Mappers.getMapper(MenuNodeMapper.class);

    MenuNode menuToMenuNode(Menu menu);
}
