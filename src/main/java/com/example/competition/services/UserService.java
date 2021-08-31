package com.example.competition.services;

import com.example.competition.models.entity.Evaluation;
import com.example.competition.models.entity.UserEntity;
import com.example.competition.models.entity.UserRoleEntity;
import com.example.competition.models.service.UserRegistrationServiceModel;
import com.example.competition.models.view.UserViewModel;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public interface UserService {

    void initUsers();

    void registerAndLoginUser(UserRegistrationServiceModel userRegistrationServiceModel);

    boolean usernameExists(String username);

    UserEntity findByUsername(String userName);


    LinkedList<UserViewModel> getAll();

    void deleteUser(String id);

    Set<String> findAllUsernamesExceptCurrent();

    List<UserRoleEntity> changeRole(String username, List<String> roles);
    void setEvaluationsOfEvaluator(Evaluation evaluation, String id);

    void removeEvaluationsWhenEditing(Set<Evaluation> toDelete, String id);

}
