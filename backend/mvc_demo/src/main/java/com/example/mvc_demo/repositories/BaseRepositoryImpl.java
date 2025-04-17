package com.example.mvc_demo.repositories;

import com.example.mvc_demo.entities.BaseEntity;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import java.util.UUID;

public class BaseRepositoryImpl<T extends BaseEntity> extends SimpleJpaRepository<T, UUID> implements BaseRepository<T> {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private Class<T> domainClass;

    public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
        this.domainClass = entityInformation.getJavaType();
    }

    // @Transactional
    // @Override
    // public T softDelete(UUID id) {
    //     T entity = entityManager.find(domainClass, id);
    //     if (entity != null) {
    //         // entity.setDeleted(true); // sử dụng setter thay vì reflection
    //         entityManager.merge(entity);
    //         return entity;
    //     }
    //     return null;
    // }
}
