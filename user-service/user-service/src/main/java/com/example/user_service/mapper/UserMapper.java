package com.example.user_service.mapper;

import com.example.user_service.event.UserEvent;
import com.example.user_service.model.UserModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEvent toDO(UserModel userModel);

    UserModel toTEntity(UserEvent dto);
}
