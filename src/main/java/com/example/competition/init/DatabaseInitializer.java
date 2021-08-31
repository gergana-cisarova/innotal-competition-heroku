package com.example.competition.init;
import com.example.competition.services.UserRoleService;
import com.example.competition.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer implements CommandLineRunner {
    private final UserRoleService userRoleService;
    private final UserService userService;

    public DatabaseInitializer(UserRoleService userRoleService, UserService userService) {

        this.userRoleService = userRoleService;
        this.userService = userService;

    }

    @Override
    public void run(String... args) throws Exception {

        userRoleService.initUserRoles();
        userService.initUsers();


    }


}