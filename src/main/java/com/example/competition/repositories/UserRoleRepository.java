package com.example.competition.repositories;


import com.example.competition.models.entity.UserRoleEntity;
import com.example.competition.models.entity.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, String> {

    Optional< UserRoleEntity> findByRole(UserRole role);

}
