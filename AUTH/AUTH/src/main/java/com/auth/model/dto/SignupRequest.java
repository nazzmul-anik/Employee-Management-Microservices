package com.auth.model.dto;

import com.auth.model.enums.Role;
import lombok.Data;

import java.util.Set;

@Data
public class SignupRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private Set<Role> roles;
}
