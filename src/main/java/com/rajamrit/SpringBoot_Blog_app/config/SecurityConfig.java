package com.rajamrit.SpringBoot_Blog_app.config;

import com.rajamrit.SpringBoot_Blog_app.Security.CustomUserDetailService;
import com.rajamrit.SpringBoot_Blog_app.Security.JwtAuthenticationEntryPoint;
import com.rajamrit.SpringBoot_Blog_app.Security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/*
BASIC AUTH FLOW ->

When a user tries to access a protected resource using Basic Authentication in a Spring Boot application with a SQL database,
several steps are involved. Here's the typical flow of events:

1. Client Request: The user's browser or application sends an HTTP request to the Spring Boot application to access
a protected resource (e.g., a REST API endpoint).

2. Server Request Handling: The Spring Boot application receives the incoming HTTP request.

3. Security Filter Chain: Spring Security's filter chain intercepts the request. The Basic Authentication
filter is a part of this chain.

4. Basic Authentication: The Basic Authentication filter checks if the request contains Basic Authentication credentials.
Basic Authentication credentials are typically encoded as a Base64-encoded string in the format "username:password".

5. User Details Lookup: Spring Security's authentication manager, which includes the DaoAuthenticationProvider configured
in your SecurityConfig, is invoked. This provider uses the UserDetailsService (implemented in your CustomUserDetailService)
to retrieve user details (like roles and password) from the SQL database based on the provided username.

6. Password Validation: The DaoAuthenticationProvider uses the PasswordEncoder to validate the provided password
against the hashed password stored in the database.

7. Authentication Result: If the password is validated successfully, an authenticated user object is created
and stored in the security context.

8. Access Granted: With a successful authentication, the user is granted access to the protected resource.

8. Response: The Spring Boot application processes the request and generates an appropriate response,
depending on the logic of the resource being accessed.

9. HTTP Response: The response is sent back to the user's browser or application.

 */

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    public CustomUserDetailService customUserDetailService;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

            http
                    .csrf(AbstractHttpConfigurer::disable)
                    .cors(AbstractHttpConfigurer::disable)
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers("/api/users/").permitAll()
                            .requestMatchers("/api/users/**").authenticated());

            http.authenticationProvider(daoAuthenticationProvider());
            return http.build();


        // JWT
        /*
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/login").permitAll() // Exclude this route from authentication
                        .anyRequest().authenticated()
                );
                .exceptionHandling(ex -> ex.authenticationEntryPoint(this.jwtAuthenticationEntryPoint))
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.authenticationProvider(daoAuthenticationProvider());
        return http.build();

         */
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(this.customUserDetailService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

}
