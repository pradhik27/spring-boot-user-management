package com.pradhik.authapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class CreateUserRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email format is invalid")
    private String email;

    public CreateUserRequest() {}

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
