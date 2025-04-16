package com.example.mvc_demo.common.mappers;
import java.util.List;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")
public interface BaseMapper<E, D> {
    D toDTO(E entity);
    E toEntity(D dto);
    List<D> toDTOs(List<E> entities);
    List<E> toEntities(List<D> dtos);
}