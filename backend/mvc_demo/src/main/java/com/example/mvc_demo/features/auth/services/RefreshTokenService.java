package com.example.mvc_demo.features.auth.services;

import com.example.mvc_demo.features.auth.models.RefreshTokenDTO;
import com.example.mvc_demo.services.BaseService;
import com.example.mvc_demo.features.user.models.UserDTO;
import com.example.mvc_demo.security.services.JwtService;
import com.example.mvc_demo.common.mappers.BaseMapper;
import com.example.mvc_demo.features.auth.models.RefreshToken;
import com.example.mvc_demo.features.auth.repositories.RefreshTokenRepository;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;


@Service
public class RefreshTokenService extends BaseService<RefreshToken, RefreshTokenDTO> {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtService jwtService;

    public RefreshTokenService(
            RefreshTokenRepository refreshTokenRepository,
            BaseMapper<RefreshToken, RefreshTokenDTO> mapper,
            JwtService jwtService
    ) {
        super(refreshTokenRepository, RefreshToken.class, RefreshTokenDTO.class, mapper);
        this.refreshTokenRepository = refreshTokenRepository;
        this.jwtService = jwtService;
    }

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