package com.pragma.emason.infrastructure.configuration;


import com.pragma.emason.infrastructure.jwt.JwtAuthenticationFilter;
import com.pragma.emason.infrastructure.util.ApiDocumentationConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor

public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests

                                .requestMatchers(ApiDocumentationConstants.CATEGORY_PATH).hasAuthority(ApiDocumentationConstants.AUTHORITY_ADMIN)
                                .requestMatchers(ApiDocumentationConstants.BRAND_PATH).hasAuthority(ApiDocumentationConstants.AUTHORITY_ADMIN)
                                .requestMatchers(ApiDocumentationConstants.ITEM_PATH).hasAuthority(ApiDocumentationConstants.AUTHORITY_AUX_BODEGA)
                                .anyRequest().authenticated()
                )
                .sessionManagement(sessionManagement ->
                        sessionManagement
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Every request must be authenticated with a token
                )
                // Verify the user credentials
                // if the authentication is successful, create a authentication object which will have the user details like its roles and authorities.
                //.authenticationProvider(authProvider)
                // Verify the jwt token before to sing in
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

}

