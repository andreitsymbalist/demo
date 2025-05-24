package com.pioneerpixel.demo.adapter.repository;

import com.pioneerpixel.demo.business.api.repository.PhoneRepository;
import com.pioneerpixel.demo.domain.PhoneData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PhoneRepositoryAdapter implements PhoneRepository {

    private final PhoneJpaRepository repository;

    @Override
    public boolean exists(String phone) {
        return repository.existsByPhone(phone);
    }

    @Override
    public void save(PhoneData phoneData) {
        repository.save(phoneData);
    }

    @Override
    public void update(Long id, Long userId, String phone) {
        int affectedRowsCount = repository.update(id, userId, phone);
        if (affectedRowsCount == 0) {
            throw new IllegalArgumentException(
                String.format("There is no phone: userId = %s, phoneId = %s", userId, id)
            );
        }
    }

    @Override
    public void delete(Long phoneId, Long userId) {
        int affectedRowsCount = repository.deleteByIdAndUserId(phoneId, userId);
        if (affectedRowsCount == 0) {
            throw new IllegalArgumentException(
                String.format("There is no phone: userId = %s, phoneId = %s", userId, phoneId)
            );
        }
    }
}
