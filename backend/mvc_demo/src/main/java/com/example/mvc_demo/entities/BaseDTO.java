package com.example.mvc_demo.entities;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public abstract class BaseDTO {
    private UUID id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}   