package com.example.mvc_demo.controllers;
import com.example.mvc_demo.security.services.JwtService;
import com.example.mvc_demo.services.AuthService;
import com.example.mvc_demo.services.RefreshTokenService;
import com.example.mvc_demo.services.UserDetailsImpl;
import com.example.mvc_demo.DTOs.RefreshTokenDTO;
import com.example.mvc_demo.DTOs.UserDTO;
import com.example.mvc_demo.DTOs.payload.resquests.LoginRequest;
import com.example.mvc_demo.DTOs.payload.resquests.SignupRequest;
import com.example.mvc_demo.common.exceptions.BadRequestException;
// import com.example.mvc_demo.common.exceptions.NotFoundException;
import com.example.mvc_demo.common.exceptions.TokenRefreshException;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;


import java.util.Map;
import java.util.HashMap;


// @CrossOrigin(origins = "*", maxAge = 3600)
// for Angular Client (withCredentials)
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private  AuthService authService;
    @Autowired
    private  JwtService jwtService;
    @Autowired
    private  AuthenticationManager authenticationManager;
    @Autowired
    private  PasswordEncoder encoder;
    @Autowired
    private  RefreshTokenService refreshTokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        // Tạo access token
        String accessToken = jwtService.generateAccessToken(userDetails.getUsername());

        // Tạo và lưu refresh token
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userDetails.getId());
        userDTO.setUsername(userDetails.getUsername());
        RefreshTokenDTO refreshToken = refreshTokenService.createRefreshToken(userDTO);

        // Tạo cookie chứa refresh token
        ResponseCookie refreshCookie = ResponseCookie.from("refreshToken", refreshToken.getToken())
                .httpOnly(true)
                .path("/api/auth/refresh")
                .maxAge(7 * 24 * 60 * 60)
                .secure(true)
                .sameSite("Strict")
                .build();

        Map<String, Object> response = new HashMap<>();
        response.put("accessToken", accessToken);
        response.put("username", userDetails.getUsername());
        response.put("email", userDetails.getEmail());
        response.put("roles", userDetails.getAuthorities());

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, refreshCookie.toString())
                .body(response);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody SignupRequest request) {
        if (authService.existsByUsername(request.getUsername())) {
            throw new BadRequestException("Username already exists");
        }

        if (authService.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email already exists");
        }

        UserDTO user = new UserDTO(
                request.getUsername(),
                encoder.encode(request.getPassword()),
                request.getName(),
                request.getEmail()
        );

        authService.register(user);
        return ResponseEntity.ok(Map.of("message", "User registered successfully"));
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@CookieValue(value = "refreshToken", required = false) String refreshTokenValue) {
        if (refreshTokenValue == null || !jwtService.isTokenValid(refreshTokenValue)) {
            throw new TokenRefreshException(refreshTokenValue, "Your token is invalid or expired");
        }

        String username = jwtService.extractUsername(refreshTokenValue);
        String newAccessToken = jwtService.generateAccessToken(username);

        return ResponseEntity.ok(Map.of("accessToken", newAccessToken));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.getPrincipal() instanceof UserDetailsImpl userDetails) {
            refreshTokenService.deleteByUserId(userDetails.getId());
        }

        ResponseCookie cookie = ResponseCookie.from("refreshToken", "")
                .path("/api/auth/refresh")
                .httpOnly(true)
                .maxAge(0)
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(Map.of("message", "Log out successful!"));
    }

}