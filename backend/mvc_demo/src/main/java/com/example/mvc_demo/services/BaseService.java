package com.example.mvc_demo.services;

import com.example.mvc_demo.DTOs.BaseDTO;
import com.example.mvc_demo.entities.BaseEntity;
import com.example.mvc_demo.mappers.BaseMapper;
import com.example.mvc_demo.repositories.BaseRepository;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

public class BaseService<E extends BaseEntity, D extends BaseDTO, R extends BaseRepository<E>, M extends BaseMapper<E, D>> {
    // @Autowired
    protected  R repository;
    // @Autowired
    protected E entity;
    // @Autowired
    protected D entityDTO;
    // @Autowired
    protected M mapper;

    // public BaseService(BaseRepository<E> repository,
    //                    Class<E> entityClass,
    //                    Class<D> dtoClass,
    //                    BaseMapper<E, D> mapper) {
        
    // }
    public List<D> getAll() {
        List<E> entities = (List<E>) repository.findAll();
        return mapper.toDTOs(entities);
    }

    public D getById(UUID id) {
        E entity = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Entity not found with id: " + id));
        return mapper.toDTO(entity);
    }

    public D save(D dto) {
        E entity = mapper.toEntity(dto);
        E saved = repository.save(entity);
        return mapper.toDTO(saved);
    }

    public void delete(UUID id) {
        E entity = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Entity not found with id: " + id));
        repository.delete(entity);
    }
}
