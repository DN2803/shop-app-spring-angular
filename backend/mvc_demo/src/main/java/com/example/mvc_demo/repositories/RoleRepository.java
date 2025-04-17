package com.example.mvc_demo.repositories;
import java.util.Optional;
import org.springframework.stereotype.Repository;

import com.example.mvc_demo.entities.ERole;
import com.example.mvc_demo.entities.Role;


@Repository
public interface RoleRepository extends BaseRepository<Role>{
    public Optional<Role> findByName(ERole name);
}
