package com.example.mvc_demo.features.auth.models;

import com.example.mvc_demo.entities.BaseEntity;
import com.example.mvc_demo.features.user.models.User;

import java.time.Instant;
import jakarta.persistence.*;

@Entity
@Table(name = "refresh_tokens")
public class RefreshToken extends BaseEntity {


    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    private Instant expiryDate;

    // Getters & Setters
}