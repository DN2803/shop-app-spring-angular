package com.example.mvc_demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import com.example.mvc_demo.entities.BaseEntity;
import java.util.UUID;
@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity> extends JpaRepository<T, UUID> {
    // T softDelete(UUID id);
}