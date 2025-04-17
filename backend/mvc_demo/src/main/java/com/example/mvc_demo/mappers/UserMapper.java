package com.example.mvc_demo.mappers;

import com.example.mvc_demo.DTOs.UserDTO;
import com.example.mvc_demo.entities.User;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<User, UserDTO> {
    UserMapper INSTANCE =  org.mapstruct.factory.Mappers.getMapper(UserMapper.class);
    @Override
    UserDTO toDTO(User entity);

    @Override
    User toEntity(UserDTO dto);

    @Override
    List<UserDTO> toDTOs(List<User> entities);

    @Override
    List<User> toEntities(List<UserDTO> dtos);
}
