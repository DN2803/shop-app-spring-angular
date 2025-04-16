package com.example.mvc_demo.features.user.services;

import org.springframework.stereotype.Service;

import com.example.mvc_demo.features.user.mappers.UserMapper;
import com.example.mvc_demo.features.user.models.User;
import com.example.mvc_demo.features.user.models.UserDTO;
import com.example.mvc_demo.features.user.repositories.UserRepository;
import com.example.mvc_demo.services.BaseService;

@Service
public class UserService extends BaseService<User,UserDTO>{
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        super(userRepository, User.class, UserDTO.class, userMapper);
    }
    public boolean register(UserDTO userDTO) {
        // Check if the username already exists
        boolean userExists = ((UserRepository) repository).findByUsername(userDTO.getUsername()).isPresent();
        if (userExists) {
            return false; // Username already taken
        }
        // Save the new user
        User user = mapper.toEntity(userDTO);
        this.repository.save(user);
        return true; // Registration successful
    }
    public boolean updateUser(UserDTO userDTO) {
        // Find the user by ID
        User user = repository.findById(userDTO.getId())
                      .orElse(null);
        if (user == null) return false;
        user = mapper.toEntity(userDTO);
        repository.save(user);
        return true; // Update successful
    }
    public boolean existsByUsername(String username) {
        return  ((UserRepository) this.repository).findByUsername(username).isPresent();
    }
    public boolean existsByEmail(String email) {
        return  ((UserRepository) this.repository).findByEmail(email).isPresent();
    }


}
