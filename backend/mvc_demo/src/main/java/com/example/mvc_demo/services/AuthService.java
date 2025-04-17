package com.example.mvc_demo.services;
import com.example.mvc_demo.DTOs.UserDTO;
import com.example.mvc_demo.entities.User;
import com.example.mvc_demo.mappers.UserMapper;
import com.example.mvc_demo.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService extends BaseService<User,UserDTO, UserRepository, UserMapper> {
    @Autowired
    private UserService userService;

    public boolean register(UserDTO userDTO) {
        // Save the new user
        User user = UserMapper.INSTANCE.toEntity(userDTO);
        this.repository.save(user);
        return true; // Registration successful
    }
    public boolean updateUser(UserDTO userDTO) {
        // Find the user by ID
        User user = repository.findById(userDTO.getId())
                                .orElse(null);
        if (user == null) {
            return false; // User not found
        }
        // Update the user details
        user = mapper.toEntity(userDTO);
        repository.save(user);
        return true; // Update successful
    }
    public boolean existsByUsername(String username) {
        return  userService.existsByUsername(username);
    }
    public boolean existsByEmail(String email) {
        return userService.existsByEmail(email);
    }


}