package com.pioneerpixel.demo.adapter.repository;

import com.pioneerpixel.demo.business.api.repository.EmailRepository;
import com.pioneerpixel.demo.domain.EmailData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EmailRepositoryAdapter implements EmailRepository {

    private final EmailJpaRepository repository;

    @Override
    public boolean exists(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public void save(EmailData emailData) {
        repository.save(emailData);
    }

    @Override
    public void update(Long id, Long userId, String email) {
        int affectedRowsCount = repository.update(id, userId, email);
        if (affectedRowsCount == 0) {
            throw new IllegalArgumentException(
                String.format("There is no email: userId = %s, emailId = %s", userId, id)
            );
        }
    }

    @Override
    public void delete(Long emailId, Long userId) {
        int affectedRowsCount = repository.deleteByIdAndUserId(emailId, userId);
        if (affectedRowsCount == 0) {
            throw new IllegalArgumentException(
                String.format("There is no email: userId = %s, emailId = %s", userId, emailId)
            );
        }

    }
}
