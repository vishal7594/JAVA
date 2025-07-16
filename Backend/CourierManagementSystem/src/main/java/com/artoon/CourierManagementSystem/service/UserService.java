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

        System.out.println("username: " + user.getUsername());
        System.out.println("token: " + user.getToken());

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
        System.out.println("Authenticating user: " + username + " with password: " + password) ;
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);

        User user = userRepository.findByUsername(username);
//        if (!passwordEncoder.matches(password, user.getPassword())) {
//            throw new RuntimeException("Password does not match");
//        }

        System.out.println("Encoded password in DB: " + user.getPassword());
        System.out.println("Raw password from login: " + password);
        System.out.println("Password match? " + passwordEncoder.matches(password, user.getPassword()));

        try {
            manager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Credentials Invalid !!");
        }
    }

    public UserLoginResponse loginUser(UserLoginRequest userRequest) {

        // get details from token

        boolean isTokenValid = true;

        UserDetails userDetails = null;
        User user = null;

        if(!userRequest.getToken().isBlank() && helper.validateToken(userRequest.getToken()))
        {
                String username = helper.getUsernameFromToken(userRequest.getToken());
                user = userRepository.findByUsername(username);
                // 5. Return Auth Response
                return new UserLoginResponse(user.getId(), user.getUsername(), user.getRole(), userRequest.getToken());
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
        return new UserLoginResponse(user.getId(), user.getUsername(), user.getRole(), token);

    }
}
