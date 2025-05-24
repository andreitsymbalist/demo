package com.pioneerpixel.demo.infrastructure.security;

import com.pioneerpixel.demo.business.api.security.AuthenticationManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class JwtAuthenticationManager implements AuthenticationManager {

    private final JwtProvider jwtProvider;

    @Override
    public String generateToken(Long userId) {
        return jwtProvider.generateToken(userId);
    }

    @Override
    public Long getUserId() {
        return Long.valueOf(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
