package com.pioneerpixel.demo.business.usecase;

import com.pioneerpixel.demo.business.api.repository.PhoneRepository;
import com.pioneerpixel.demo.business.api.security.AuthenticationManager;
import com.pioneerpixel.demo.business.validator.PhoneValidator;
import com.pioneerpixel.demo.domain.PhoneData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CreatePhoneUseCase {

    private final PhoneRepository phoneRepository;
    private final AuthenticationManager authenticationManager;

    public void execute(String phone) {
        PhoneValidator.validateFormat(phone);
        boolean isExist = phoneRepository.exists(phone);
        if (isExist) {
            throw new IllegalArgumentException("Phone is already in use");
        }

        PhoneData phoneData = new PhoneData();
        phoneData.setPhone(phone);
        phoneData.setUserId(authenticationManager.getUserId());
        phoneRepository.save(phoneData);
    }

}
