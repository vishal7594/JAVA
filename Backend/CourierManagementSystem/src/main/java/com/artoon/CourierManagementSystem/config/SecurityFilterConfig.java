package com.artoon.CourierManagementSystem.config;

import com.artoon.CourierManagementSystem.enums.Role;
import com.artoon.CourierManagementSystem.jwt.JwtAuthenticationEntryPoint;
import com.artoon.CourierManagementSystem.jwt.JwtAuthenticationFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
public class SecurityFilterConfig {
    private JwtAuthenticationEntryPoint point;
    private JwtAuthenticationFilter filter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
        return security.csrf(csrf-> csrf.disable())
                .cors(cors-> cors.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll() // Allow register/login
                        .requestMatchers("/admin/**").hasRole(Role.ADMIN.toString())
                        .requestMatchers("/manager/**").hasRole(Role.MANAGER.toString())
                        .requestMatchers("/delivery-agent/**").hasRole(Role.DELIVERY_AGENT.toString())
                        .requestMatchers("/customer/**").hasRole(Role.CUSTOMER.toString())
                        .anyRequest().authenticated())
                .exceptionHandling(ex ->ex.authenticationEntryPoint(point))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }
}
