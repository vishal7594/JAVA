package com.artoon.CourierManagementSystem.service;

import com.artoon.CourierManagementSystem.jwt.JwtHelper;
import com.artoon.CourierManagementSystem.model.dto.request.UserRequest;
import com.artoon.CourierManagementSystem.model.dto.response.UserResponse;
import com.artoon.CourierManagementSystem.model.entity.User;
import com.artoon.CourierManagementSystem.model.UserPrincipal;
import com.artoon.CourierManagementSystem.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JwtHelper helper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
        if(user == null) throw  new UsernameNotFoundException("User not found !!");
        return  new UserPrincipal(user);
    }

    public UserResponse registerUser(UserRequest userRequest) throws RuntimeException {

        User user = modelMapper.map(userRequest,User.class);
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        return modelMapper.map(userRepository.save(user), UserResponse.class);
    }
}
