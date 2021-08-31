package com.example.competition.models.binding;


import com.example.competition.models.entity.enums.University;
import com.example.competition.validator.FieldMatch;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@FieldMatch(
        first = "password",
        second = "confirmPassword"
)
public class UserRegistrationBindingModel {
    @NotEmpty
    @Size(min=3, max = 20)
    private String username;

    private String firstName;

    @NotEmpty
    @Size(min=3, max = 20)
    private String lastName;
    @NotEmpty
    @Size(min=5, max = 20)
    private String password;

    @NotEmpty
    @Size(min=5, max = 20)
    private String confirmPassword;

    @NotNull
    private University university;

    public String getUsername() {
        return username;
    }

    public UserRegistrationBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserRegistrationBindingModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserRegistrationBindingModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegistrationBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegistrationBindingModel setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    public University getUniversity() {
        return university;
    }

    public UserRegistrationBindingModel setUniversity(University university) {
        this.university = university;
        return this;
    }
}
