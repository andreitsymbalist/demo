package com.pioneerpixel.demo.business.api.repository;

import com.pioneerpixel.demo.domain.PhoneData;

public interface PhoneRepository {

    boolean exists(String phone);

    void save(PhoneData phoneData);

    void update(Long id, Long userId, String phone);

    void delete(Long phoneId, Long userId);
}
