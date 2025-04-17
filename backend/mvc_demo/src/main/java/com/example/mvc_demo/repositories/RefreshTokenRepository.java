package com.example.mvc_demo.repositories;

import com.example.mvc_demo.entities.RefreshToken;

import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface RefreshTokenRepository extends BaseRepository<RefreshToken>{
     public void deleteByUserId(UUID userId);
}
