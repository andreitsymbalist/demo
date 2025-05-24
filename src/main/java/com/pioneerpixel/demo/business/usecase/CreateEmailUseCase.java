package com.pioneerpixel.demo.business.usecase;

import com.pioneerpixel.demo.business.api.repository.EmailRepository;
import com.pioneerpixel.demo.business.api.security.AuthenticationManager;
import com.pioneerpixel.demo.domain.EmailData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CreateEmailUseCase {

    private final EmailRepository emailRepository;
    private final AuthenticationManager authenticationManager;

    public void execute(String email) {
        boolean isExist = emailRepository.exists(email);
        if (isExist) {
            // todo: custom exception
            throw new IllegalArgumentException("Email is already in use");
        }

        EmailData emailData = new EmailData();
        emailData.setEmail(email);
        emailData.setUserId(authenticationManager.getUserId());
        emailRepository.save(emailData);
    }
}
