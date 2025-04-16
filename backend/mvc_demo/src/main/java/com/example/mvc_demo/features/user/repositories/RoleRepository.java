package com.example.mvc_demo.features.user.repositories;
import java.util.Optional;
import org.springframework.stereotype.Repository;

import com.example.mvc_demo.features.user.models.Role;
import com.example.mvc_demo.repositories.BaseRepository;
import com.example.mvc_demo.features.user.models.ERole;


@Repository
public interface RoleRepository extends BaseRepository<Role>{
    public Optional<Role> findByName(ERole name);
}
