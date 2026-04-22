package com.auth.service;

import com.auth.model.dto.JwtTokenResponse;
import com.auth.model.dto.LoginRequest;
import com.auth.model.dto.SignupRequest;
import com.auth.model.dto.UserDTO;

public interface UserService {
    UserDTO saveUser(SignupRequest signupRequest);
    JwtTokenResponse loginUser(LoginRequest loginRequest);
}
