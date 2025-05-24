package com.pioneerpixel.demo.business.usecase;

import com.pioneerpixel.demo.business.api.repository.PhoneRepository;
import com.pioneerpixel.demo.business.api.security.AuthenticationManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class DeletePhoneUseCase {

    private final PhoneRepository phoneRepository;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public void execute(Long phoneId) {
        Long userId = authenticationManager.getUserId();
        phoneRepository.delete(phoneId, userId);
    }
}
