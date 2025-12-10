package com.example.hospitalapp.service;

import com.example.hospitalapp.exception.UserAlreadyExistsException;
import com.example.hospitalapp.model.JwtRequest;
import com.example.hospitalapp.model.JwtResponse;
import com.example.hospitalapp.model.RegisterRequest;
import com.example.hospitalapp.model.entity.AppUser;
import com.example.hospitalapp.repository.AppUserRepository;
import com.example.hospitalapp.security.JwtHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager manager;
    private final JwtHelper helper;
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    // ===========================
    //         LOGIN
    // ===========================
    public JwtResponse login(JwtRequest request) {

        doAuthenticate(request.getEmail(), request.getPassword());

        UserDetails userDetails =
                userDetailsService.loadUserByUsername(request.getEmail());

        String token = helper.generateToken(userDetails);

        return JwtResponse.builder()
                .jwtToken(token)
                .username(userDetails.getUsername())
                .build();
    }

    private void doAuthenticate(String email, String password) {
        try {
            manager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password));
        } catch (BadCredentialsException ex) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }



    // ===========================
    //        REGISTER
    // ===========================
    public JwtResponse  register(RegisterRequest request) {

        // Check if user exists
        if (appUserRepository.findByUsername(request.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("User already exists with email: " + request.getEmail());
        }

        // Create new user
        AppUser user = new AppUser();
        user.setUsername(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        String role = request.getRole() != null ? request.getRole() : "USER";
        user.setRole(role.toUpperCase());

        appUserRepository.save(user);

        // After register → auto login
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        String token = helper.generateToken(userDetails);

        return JwtResponse.builder()
                .jwtToken(token)
                .username(user.getUsername())
                .build();
    }
}
