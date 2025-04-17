package com.example.mvc_demo.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO extends BaseDTO {
    private boolean verified;
    private String name;
    private String email;
    private String password;
    private String username;

    public UserDTO() {
        super();
    }
    public UserDTO(String username, String password) {
        this.password = password;
        this.username = username;
    }
    public UserDTO(String username, String password, String name, String email) {
        this.password = password;
        this.username = username;
        this.name = name;
        this.email = email;
    }
}
