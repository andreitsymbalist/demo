package com.pioneerpixel.demo.infrastructure.config;

import com.pioneerpixel.demo.infrastructure.security.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.httpBasic(HttpBasicConfigurer::disable)
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(sess -> sess
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/users/login").permitAll()
                .requestMatchers(
                    new AntPathRequestMatcher("/v3/api-docs/**", "GET"),
                    new AntPathRequestMatcher("/swagger-ui/**", "GET"),
                    new AntPathRequestMatcher("/swagger-ui.html", "GET")
                ).permitAll()
                .anyRequest().authenticated()
            )
            .addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
    }
}
