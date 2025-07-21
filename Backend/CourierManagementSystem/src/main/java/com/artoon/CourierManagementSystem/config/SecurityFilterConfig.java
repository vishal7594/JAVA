package com.artoon.CourierManagementSystem.config;

import com.artoon.CourierManagementSystem.enums.Role;
import com.artoon.CourierManagementSystem.jwt.JwtAuthenticationEntryPoint;
import com.artoon.CourierManagementSystem.jwt.JwtAuthenticationFilter;
import com.artoon.CourierManagementSystem.jwt.KeyCloakRoleReader;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collection;

@Configuration
@AllArgsConstructor
public class SecurityFilterConfig {
    private JwtAuthenticationEntryPoint point;
    private JwtAuthenticationFilter filter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
         security.csrf(csrf-> csrf.disable())
                .cors(cors-> cors.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll() // Allow register/login
                        .requestMatchers("/admin/**").hasRole(Role.ADMIN.toString())
                        .requestMatchers("/manager/**").hasRole(Role.MANAGER.toString())
                        .requestMatchers("/delivery-agent/**").hasRole(Role.DELIVERY_AGENT.toString())
                        .requestMatchers("/customer/**").hasRole(Role.CUSTOMER.toString())
                        .anyRequest().authenticated())
                 .oauth2ResourceServer(oauth2 -> oauth2.jwt(
                         jwtSpec -> jwtSpec.jwtAuthenticationConverter(getJwtAuthenticationConverter())
                 ));


         return  security.build();





//                .exceptionHandling(ex ->ex.authenticationEntryPoint(point))
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
//                .build();

    }

    private Converter<Jwt, ? extends AbstractAuthenticationToken> getJwtAuthenticationConverter() {

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeyCloakRoleReader());

        return jwtAuthenticationConverter;
    }
}
