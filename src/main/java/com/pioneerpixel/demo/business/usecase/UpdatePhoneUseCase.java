package com.pioneerpixel.demo.business.usecase;

import com.pioneerpixel.demo.business.api.repository.PhoneRepository;
import com.pioneerpixel.demo.business.api.security.AuthenticationManager;
import com.pioneerpixel.demo.business.validator.PhoneValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class UpdatePhoneUseCase {

    private final PhoneRepository phoneRepository;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public void execute(Long phoneId, String phone) {
        PhoneValidator.validateFormat(phone);

        Long userId = authenticationManager.getUserId();
        phoneRepository.update(phoneId, userId, phone);
    }
}
