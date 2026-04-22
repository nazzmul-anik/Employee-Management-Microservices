package com.auth.controller;

import com.auth.model.dto.JwtTokenResponse;
import com.auth.model.dto.LoginRequest;
import com.auth.model.dto.SignupRequest;
import com.auth.model.dto.UserDTO;
import com.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody SignupRequest signupRequest) {
        UserDTO userDTO = userService.saveUser(signupRequest);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        JwtTokenResponse jwtTokenResponse = userService.loginUser(loginRequest);
        return new ResponseEntity<>(jwtTokenResponse, HttpStatus.OK);
    }
}
