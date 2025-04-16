package com.example.mvc_demo.features.user.mappers;

import com.example.mvc_demo.common.mappers.BaseMapper;
import com.example.mvc_demo.features.user.models.User;
import com.example.mvc_demo.features.user.models.UserDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<User, UserDTO> {
    @Override
    UserDTO toDTO(User entity);

    @Override
    User toEntity(UserDTO dto);

    @Override
    List<UserDTO> toDTOs(List<User> entities);

    @Override
    List<User> toEntities(List<UserDTO> dtos);
}
