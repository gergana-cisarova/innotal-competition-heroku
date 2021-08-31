package com.example.competition.services.impl;

import com.example.competition.models.entity.Evaluation;
import com.example.competition.models.entity.UserEntity;
import com.example.competition.models.entity.UserRoleEntity;
import com.example.competition.models.entity.enums.University;
import com.example.competition.models.entity.enums.UserRole;
import com.example.competition.models.service.UserRegistrationServiceModel;
import com.example.competition.models.view.UserViewModel;
import com.example.competition.repositories.EvaluationRepository;
import com.example.competition.repositories.UserRepository;
import com.example.competition.repositories.UserRoleRepository;
import com.example.competition.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final CompetitionUserService competitionUserService;
    private final EvaluationRepository evaluationRepository;

    public UserServiceImpl(UserRoleRepository userRoleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper, CompetitionUserService competitionUserService, EvaluationRepository evaluationRepository) {
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.competitionUserService = competitionUserService;
        this.evaluationRepository = evaluationRepository;
    }

    @Override
    public void initUsers() {
        if (userRepository.count() == 0) {
            UserRoleEntity adminRole = this.userRoleRepository.findByRole(UserRole.ADMIN).orElse(null);
            UserRoleEntity userRole = this.userRoleRepository.findByRole(UserRole.USER).orElse(null);
            UserRoleEntity guestRole = this.userRoleRepository.findByRole(UserRole.GUEST).orElse(null);

            UserEntity admin = new UserEntity()
                    .setUsername("admin")
                    .setPassword(passwordEncoder.encode("12345"))
                    .setFirstName("Admin")
                    .setLastName("Adminov")
                    .setUniversity(University.ECQ);
            admin.setRoles(List.of(adminRole, userRole, guestRole));

            UserEntity rku = new UserEntity()
                    .setUsername("RKU")
                    .setPassword(passwordEncoder.encode("patel_fe1"))
                    .setFirstName("Mohit")
                    .setLastName("RKU")
                    .setUniversity(University.RK_UNIVERSITY);
            rku.setRoles(List.of(userRole));

            UserEntity ruhuna = new UserEntity()
                    .setUsername("Tilak")
                    .setPassword(passwordEncoder.encode("tilak_se2"))
                    .setFirstName("Tilak")
                    .setLastName("Ruhuna")
                    .setUniversity(University.UNIVERSITY_OF_RUHUNA);
            ruhuna.setRoles(List.of(userRole));

            UserEntity pokhara = new UserEntity()
                    .setUsername("Tek")
                    .setPassword(passwordEncoder.encode("tek_le1"))
                    .setFirstName("Tek")
                    .setLastName("Pokhara")
                    .setUniversity(University.POKHARA_UNIVERSITY);
            pokhara.setRoles(List.of(userRole));

            UserEntity steven = new UserEntity()
                    .setUsername("Steven")
                    .setPassword(passwordEncoder.encode("steve_te1"))
                    .setFirstName("Steven")
                    .setLastName("Ulster")
                    .setUniversity(University.ULSTER_UNIVERSITY);
            steven.setRoles(List.of(userRole));

            UserEntity ivan = new UserEntity()
                    .setUsername("Ivan")
                    .setPassword(passwordEncoder.encode("ivan_se1"))
                    .setFirstName("Ivan")
                    .setLastName("UNWE")
                    .setUniversity(University.UNWE);
            ivan.setRoles(List.of(userRole));

            UserEntity pantelis = new UserEntity()
                    .setUsername("Pantelis")
                    .setPassword(passwordEncoder.encode("pantelis_fe2"))
                    .setFirstName("Pantelis")
                    .setLastName("UTH")
                    .setUniversity(University.UNIVERSITY_OF_THESSALY);
            pantelis.setRoles(List.of(userRole));


            UserEntity serafin = new UserEntity()
                    .setUsername("Serafin")
                    .setPassword(passwordEncoder.encode("serafin_te2"))
                    .setFirstName("Serafin")
                    .setLastName("IFSU")
                    .setUniversity(University.IFUGAO_STATE_UNIVERSITY);
            serafin.setRoles(List.of(userRole));


            UserEntity milanka = new UserEntity()
                    .setUsername("Milanka")
                    .setPassword(passwordEncoder.encode("milanka_le2"))
                    .setFirstName("Milanka")
                    .setLastName("UNWE")
                    .setUniversity(University.UNWE);
            milanka.setRoles(List.of(userRole));


            userRepository.saveAll(List.of(rku, milanka, ruhuna, serafin, ivan, pantelis, pokhara, steven, admin));
        }
    }

    @Override
    public void registerAndLoginUser(UserRegistrationServiceModel userRegistrationServiceModel) {
        UserEntity newUser = modelMapper.map(userRegistrationServiceModel, UserEntity.class);
        newUser.setPassword(passwordEncoder.encode(userRegistrationServiceModel.getPassword()));

        UserRoleEntity guestRole = userRoleRepository.findByRole(UserRole.GUEST).orElseThrow(() -> new IllegalStateException("User role not found. Please seed the roles."));
        newUser.addRole(guestRole);
        newUser = userRepository.save(newUser);
        UserDetails principal = competitionUserService.loadUserByUsername(newUser.getUsername());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal,
                newUser.getPassword(),
                principal.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    public boolean usernameExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    public UserEntity findByUsername(String userName) {
        return userRepository.findByUsername(userName).orElse(null);
    }


    @Override
    @PostFilter("!filterObject.username.equalsIgnoreCase(authentication.name)")
    public LinkedList<UserViewModel> getAll() {
        return userRepository.findAll()
                .stream()
                .map(u -> {
                    UserViewModel userViewModel = modelMapper.map(u, UserViewModel.class);
                    userViewModel.setFullName(
                            String.format("%s %s", u.getFirstName(), u.getLastName()));
                    userViewModel.setUniversity(
                            String.format("%s", u.getUniversity()));
                    StringBuilder sb4 = new StringBuilder();
                    u.getRoles()
                            .forEach(r -> sb4.append(String.format("%s <br />", r.getRole().name())));
                    userViewModel.setRoles(sb4.toString());
                    return userViewModel;
                }).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public void deleteUser(String id) {
        UserEntity user = userRepository.getById(id);
        user.getEvaluations().forEach(e -> evaluationRepository.delete(e));
        userRepository.deleteById(id);
    }

    @Override
    @PostFilter("!filterObject.equalsIgnoreCase(authentication.name)")
    public Set<String> findAllUsernamesExceptCurrent() {
        return userRepository
                .findAll()
                .stream()
                .map(u -> u.getUsername())
                .collect(Collectors.toSet());
    }

    @Override
    public List<UserRoleEntity> changeRole(String username, List<String> roles) {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(IllegalArgumentException::new);
        List<UserRoleEntity> newRoleList = new ArrayList<>();
        roles.forEach(r -> {
            UserRoleEntity userRole = userRoleRepository.findByRole(UserRole.valueOf(r.toUpperCase())).orElseThrow(() -> new IllegalStateException("User role not found. Please seed the roles."));
            newRoleList.add(userRole);
        });
        user.setRoles(newRoleList);
        userRepository.save(user);
        return newRoleList;
    }

    @Override
    public void setEvaluationsOfEvaluator(Evaluation evaluation, String id) {
        UserEntity evaluator = this.userRepository.getById(id);
        Set<Evaluation> current = evaluator.getEvaluations();
        current.add(evaluation);
        this.userRepository.save(evaluator);
    }

    @Override
    public void removeEvaluationsWhenEditing(Set<Evaluation> toDelete, String id) {
        UserEntity evaluator = this.userRepository.getById(id);
        Set<Evaluation> current = evaluator.getEvaluations();
        toDelete.forEach(e -> current.remove(e));
        evaluator.setEvaluations(current);
        this.userRepository.save(evaluator);
    }
}



