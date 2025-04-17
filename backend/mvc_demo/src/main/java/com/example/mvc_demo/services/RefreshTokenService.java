package com.example.mvc_demo.services;

import com.example.mvc_demo.mappers.BaseMapper;
import com.example.mvc_demo.repositories.RefreshTokenRepository;
import com.example.mvc_demo.security.services.JwtService;
import com.example.mvc_demo.DTOs.RefreshTokenDTO;
import com.example.mvc_demo.DTOs.UserDTO;
import com.example.mvc_demo.entities.RefreshToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;


@Service
public class RefreshTokenService extends BaseService<RefreshToken, RefreshTokenDTO, RefreshTokenRepository, BaseMapper<RefreshToken, RefreshTokenDTO>> {
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;
    @Autowired
    private JwtService jwtService;

    // public RefreshTokenService(
    //         RefreshTokenRepository refreshTokenRepository,
    //         BaseMapper<RefreshToken, RefreshTokenDTO> mapper,
    //         JwtService jwtService
    // ) {
    //     super(refreshTokenRepository, RefreshToken.class, RefreshTokenDTO.class, mapper);
    //     this.refreshTokenRepository = refreshTokenRepository;
    //     this.jwtService = jwtService;
    // }

    public RefreshTokenDTO createRefreshToken(UserDTO user) {
        String refreshToken = jwtService.generateRefreshToken(user.getUsername());

        RefreshTokenDTO tokenDTO = new RefreshTokenDTO();
        tokenDTO.setUser(user);
        tokenDTO.setToken(refreshToken);
        tokenDTO.setExpiryDate(Date.from(Instant.now().plus(7, ChronoUnit.DAYS)));

        RefreshToken savedToken = refreshTokenRepository.save(this.mapper.toEntity(tokenDTO));
        return this.mapper.toDTO(savedToken);
    }

    public boolean isExpired(RefreshTokenDTO token) {
        if (token == null || token.getExpiryDate() == null) {
            return true;
        }
        return token.getExpiryDate().before(new Date());
    }

    public void deleteByUserId(UUID userId) {
        refreshTokenRepository.deleteByUserId(userId);
    }
}