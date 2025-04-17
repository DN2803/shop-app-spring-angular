package com.example.mvc_demo.repositories;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.mvc_demo.entities.User;

import java.util.List;

@Repository
public interface UserRepository extends BaseRepository<User> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    List<User> findByRoles_Name(String role);
}