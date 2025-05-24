package com.pioneerpixel.demo.business.api.security;

public interface AuthenticationManager {
    String generateToken(Long userId);

    Long getUserId();
}
