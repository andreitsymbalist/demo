package com.pioneerpixel.demo.business.usecase;

import com.pioneerpixel.demo.business.api.repository.EmailRepository;
import com.pioneerpixel.demo.business.api.security.AuthenticationManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class UpdateEmailUseCase {
    
    private final EmailRepository emailRepository;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public void execute(Long emailId, String email) {
        Long userId = authenticationManager.getUserId();
        emailRepository.update(emailId, userId, email);
    }
}
