package com.example.competition.services.impl;

import com.example.competition.models.entity.UserRoleEntity;
import com.example.competition.models.entity.enums.UserRole;
import com.example.competition.repositories.UserRoleRepository;
import com.example.competition.services.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;


    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void initUserRoles() {
        if (userRoleRepository.count() == 0) {
            UserRoleEntity adminRole = new UserRoleEntity().setRole(UserRole.ADMIN);
            UserRoleEntity userRole = new UserRoleEntity().setRole(UserRole.USER);
            UserRoleEntity guestRole = new UserRoleEntity().setRole(UserRole.GUEST);
            this.userRoleRepository.saveAll(List.of(adminRole, userRole, guestRole));
        }

    }
}
