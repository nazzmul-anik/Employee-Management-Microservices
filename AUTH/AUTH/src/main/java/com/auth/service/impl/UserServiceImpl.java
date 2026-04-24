package com.auth.service.impl;

import com.auth.model.dto.JwtTokenResponse;
import com.auth.model.dto.LoginRequest;
import com.auth.model.dto.SignupRequest;
import com.auth.model.dto.UserDTO;
import com.auth.model.entity.User;
import com.auth.repository.UserRepository;
import com.auth.service.UserService;
import com.auth.util.JwtUtil;
import com.commonlib.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Override
    public UserDTO saveUser(SignupRequest signupRequest) {
        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setFirstName(signupRequest.getFirstName());
        user.setLastName(signupRequest.getLastName());
        user.setUsername(signupRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.setRoles(signupRequest.getRoles());
        userRepository.save(user);
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public JwtTokenResponse loginUser(LoginRequest loginRequest) {

        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            return generateJwtToken(authentication.getName());

        }catch(BadCredentialsException e){
            throw new BadRequestException("Invalid username or password");
        }

    }

    public JwtTokenResponse generateJwtToken(String username){
        String token = jwtUtil.generateToken(username);
        JwtTokenResponse jwtTokenResponse = new JwtTokenResponse();
        jwtTokenResponse.setToken(token);
        jwtTokenResponse.setType("Bearer");
        jwtTokenResponse.setValidUntil(jwtUtil.getExpiration(token).toString());
        return jwtTokenResponse;
    }
}
