package com.example.competition.models.view;

public class UserViewModel {


    private String id;
    private String username;
    private String fullName;
    private String university;
    private String roles;

    public UserViewModel() {
    }

    public String getId() {
        return id;
    }

    public UserViewModel setId(String id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserViewModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public UserViewModel setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getUniversity() {
        return university;
    }

    public UserViewModel setUniversity(String university) {
        this.university = university;
        return this;
    }

    public String getRoles() {
        return roles;
    }

    public UserViewModel setRoles(String roles) {
        this.roles = roles;
        return this;
    }
}
