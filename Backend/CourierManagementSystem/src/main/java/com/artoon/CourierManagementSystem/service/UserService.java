package com.artoon.CourierManagementSystem.service;

import com.artoon.CourierManagementSystem.enums.Role;
import com.artoon.CourierManagementSystem.jwt.JwtHelper;
import com.artoon.CourierManagementSystem.model.dto.request.UserLoginRequest;
import com.artoon.CourierManagementSystem.model.dto.request.UserSignupRequest;
import com.artoon.CourierManagementSystem.model.dto.response.UserLoginResponse;
import com.artoon.CourierManagementSystem.model.dto.response.UserSignupResponse;
import com.artoon.CourierManagementSystem.model.entity.User;
import com.artoon.CourierManagementSystem.model.UserPrincipal;
import com.artoon.CourierManagementSystem.repository.UserRepository;
import io.jsonwebtoken.ExpiredJwtException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JwtHelper helper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
        System.out.println("Loading user by username: " + username);
        if(user == null) throw  new UsernameNotFoundException("User not found !!");
        return  new UserPrincipal(user);
    }

    public UserSignupResponse registerUser(UserSignupRequest userRequest) throws RuntimeException {

        // 1. Check if user already exists
        if (userRepository.existsByUsername(userRequest.getUsername())) {
            throw new RuntimeException("User already exists with this username");
        }

        if (userRequest.getUsername() == null || userRequest.getUsername().isEmpty()) {
            throw new RuntimeException("Username cannot be empty");
        }

        List<String> validRoles = Arrays.stream(Role.values())
                .map(Enum::name)
                .collect(Collectors.toList());

        if (userRequest.getRole().toString().isBlank() || !validRoles.contains(userRequest.getRole().toUpperCase())) {
            throw new RuntimeException("Invalid role provided. Accepted roles are: " + validRoles);
        }

        // 2. Create and save user
        User user = modelMapper.map(userRequest,User.class);
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user = userRepository.save(user);

        // 3. Auto-login: generate token

        UserDetails userDetails = loadUserByUsername(userRequest.getUsername());
        String token = this.helper.generateToken(userDetails);

        user.setToken(token);
        userRepository.save(user);

        // 4. Return response with token
        UserSignupResponse userResponse = new UserSignupResponse(user.getId(),
                user.getUsername(),
                user.getRole(),
                token);
            return  userResponse;
    }

    private void doAuthenticate(String username, String password) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);

        User user = userRepository.findByUsername(username);
        try {
            manager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Credentials Invalid !!");
        }
    }

    public UserLoginResponse loginUser(UserLoginRequest userRequest, String authToken) throws RuntimeException {
        // get details from token
        UserDetails userDetails = null;
        User user = null;

        if (authToken != null && !authToken.isBlank()) {
            authToken = authToken.replace("Bearer ", "").trim();
            System.out.println("Auth Token after trim: " + authToken);

            try {
                if (helper.validateToken(authToken)) {
                    String username = helper.getUsernameFromToken(authToken);
                    user = userRepository.findByUsername(username);
                    return new UserLoginResponse(user.getId(), user.getUsername(), user.getRole(), authToken);
                }
            } catch (ExpiredJwtException e) {
                System.out.println("Token expired — continue with normal login flow");
            } catch (Exception e) {
                throw  new RuntimeException("Invalid token: " + e.getMessage());
            }
        }

        // 1. Authenticate user
        doAuthenticate(userRequest.getUsername(), userRequest.getPassword());

        // 2. Load user details
        userDetails = loadUserByUsername(userRequest.getUsername());

        // 3. Generate JWT token
        String token = helper.generateToken(userDetails);

        // 4. Fetch user from DB
         user = userRepository.findByUsername(userRequest.getUsername());
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        // 5. Return Auth Response
        return modelMapper.map(user, UserLoginResponse.class);
    }

    public UserLoginResponse getCurrentUser(String token) throws  RuntimeException {
        if (token == null || token.isBlank()) {
            throw new RuntimeException("Token is required to get current user");
        }

        token = token.replace("Bearer ", "").trim();
        System.out.println("Auth Token after trim: " + token);

        if (!helper.validateToken(token)) {
            throw new RuntimeException("Invalid or expired token");
        }

        String username = helper.getUsernameFromToken(token);
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        return  modelMapper.map(user, UserLoginResponse.class);
    }
}
