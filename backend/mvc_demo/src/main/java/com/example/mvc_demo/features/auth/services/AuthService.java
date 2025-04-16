package com.example.mvc_demo.features.auth.services;
import com.example.mvc_demo.services.BaseService;
import com.example.mvc_demo.features.user.models.User;
import com.example.mvc_demo.features.user.models.UserDTO;
import com.example.mvc_demo.features.user.repositories.UserRepository;
import com.example.mvc_demo.features.user.services.UserService;
import com.example.mvc_demo.features.user.mappers.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class AuthService extends BaseService<User,UserDTO>{
    private final UserService userService;

    public AuthService(UserRepository repository, UserMapper mapper, UserService userService) {
        super(repository, User.class, UserDTO.class, mapper);
        this.userService = userService;
    }
    public boolean register(UserDTO userDTO) {
        // Save the new user
        User user = mapper.toEntity(userDTO);
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