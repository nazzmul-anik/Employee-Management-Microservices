package com.auth.model.dto;

import com.auth.model.enums.Role;
import lombok.Data;

import java.util.Set;

@Data
public class UserDTO {
    private Long id;
    private String email;
    private String username;
    private Set<Role> roles;
}
