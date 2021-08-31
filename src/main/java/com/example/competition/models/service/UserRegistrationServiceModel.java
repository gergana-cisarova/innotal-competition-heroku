package com.example.competition.models.service;

import com.example.competition.models.entity.enums.University;

public class UserRegistrationServiceModel {
    private String id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private University university;

    public UserRegistrationServiceModel() {
    }

    public UserRegistrationServiceModel setId(String id) {
        this.id = id;
        return this;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public UserRegistrationServiceModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegistrationServiceModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserRegistrationServiceModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserRegistrationServiceModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public University getUniversity() {
        return university;
    }

    public UserRegistrationServiceModel setUniversity(University university) {
        this.university = university;
        return this;
    }

}
