package com.pioneerpixel.demo.adapter.repository;

import com.pioneerpixel.demo.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountJpaRepository extends JpaRepository<Account, Long> {
    Account findByUserId(Long userId);
}
