package com.example.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;


public class UserRegistrationDTO {
    @NotEmpty(message = "First name must not be Empty!")
    private String firstName;
    @NotEmpty(message = "Last name must not be Empty!")
    private String lastName;
    @NotEmpty(message = "Email must not be Empty!")
    @Email
    private String email;

    @NotEmpty(message = "Password must not be Empty!")
    private String password;

    private String confirmPassword;

    @NotEmpty(message = "Profile image URL  is required!")
    @Length(max = 200,message = "Profile image URL  is too long!")
    private String profilePictureUrl;

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

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


