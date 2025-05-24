package com.pioneerpixel.demo.business.usecase;

import com.pioneerpixel.demo.business.api.repository.UserRepository;
import com.pioneerpixel.demo.business.api.security.AuthenticationManager;
import com.pioneerpixel.demo.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class LoginUseCase {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    public String execute(String password, String phone) {
        return userRepository.get(password, phone)
            .map(User::getId)
            .map(authenticationManager::generateToken)
            .orElseThrow(() -> new IllegalArgumentException("Invalid auth data"));
    }
}
