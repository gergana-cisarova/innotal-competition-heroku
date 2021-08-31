package com.example.competition.repositories;

import com.example.competition.models.entity.UserEntity;
import com.example.competition.models.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    Optional<UserEntity> findByUsername(String username);
    List<UserEntity> findByRolesNotContaining(UserRoleEntity role);
}
