package com.example.user_service.mapper;

import com.example.user_service.event.UserEvent;
import com.example.user_service.model.UserModel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-10T13:37:30+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserEvent toDO(UserModel userModel) {
        if ( userModel == null ) {
            return null;
        }

        UserEvent userEvent = new UserEvent();

        userEvent.setUserId( userModel.getUserId() );
        userEvent.setName( userModel.getName() );
        userEvent.setEmail( userModel.getEmail() );
        userEvent.setPhone( userModel.getPhone() );

        return userEvent;
    }

    @Override
    public UserModel toTEntity(UserEvent dto) {
        if ( dto == null ) {
            return null;
        }

        UserModel userModel = new UserModel();

        userModel.setUserId( dto.getUserId() );
        userModel.setName( dto.getName() );
        userModel.setEmail( dto.getEmail() );
        userModel.setPhone( dto.getPhone() );

        return userModel;
    }
}
