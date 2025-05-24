package com.pioneerpixel.demo.business.api.repository;

import com.pioneerpixel.demo.domain.EmailData;

public interface EmailRepository {
    boolean exists(String email);

    void save(EmailData emailData);

    void update(Long phoneId, Long userId, String email);

    void delete(Long phoneId, Long userId);
}
