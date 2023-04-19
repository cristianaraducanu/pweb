package com.foodiefrenzy.mapper;

import com.foodiefrenzy.dto.UserDTO;
import com.foodiefrenzy.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface UserMapper extends EntityMapper<UserDTO, User> {
}
