package com.example.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;




public class UserRegistrationDTO {
    @NotEmpty(message = "First name must not be Empty!")
    private String firstName;
    @NotEmpty(message = "Last name must not be Empty!")
    private String lastName;
    @NotEmpty(message = "Email must not be Empty!")
    @Email
    private String email;
    private String password;
    private String confirmPassword;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String fullName() {
        return firstName + " " + lastName;
    }
}


