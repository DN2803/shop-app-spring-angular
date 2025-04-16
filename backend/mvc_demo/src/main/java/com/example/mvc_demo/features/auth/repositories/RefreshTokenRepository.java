package com.example.mvc_demo.features.auth.repositories;

import com.example.mvc_demo.repositories.BaseRepository;
import com.example.mvc_demo.features.auth.models.RefreshToken;

import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface RefreshTokenRepository extends BaseRepository<RefreshToken>{
     public void deleteByUserId(UUID userId);
}
