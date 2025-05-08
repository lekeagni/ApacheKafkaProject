package com.example.user_service.mapper;

import com.example.user_service.event.UserDTO;
import com.example.user_service.model.UserModel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-08T11:04:20+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO toDO(UserModel userModel) {
        if ( userModel == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setUserId( userModel.getUserId() );
        userDTO.setName( userModel.getName() );
        userDTO.setEmail( userModel.getEmail() );
        userDTO.setPhone( userModel.getPhone() );

        return userDTO;
    }

    @Override
    public UserModel toTEntity(UserDTO dto) {
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
