package com.pioneerpixel.demo.business.usecase;

import com.pioneerpixel.demo.business.api.repository.EmailRepository;
import com.pioneerpixel.demo.business.api.security.AuthenticationManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class DeleteEmailUseCase {

    private final EmailRepository emailRepository;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public void execute(Long emailId) {
        Long userId = authenticationManager.getUserId();
        emailRepository.delete(emailId, userId);
    }
}
