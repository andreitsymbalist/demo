package com.pioneerpixel.demo.adapter.repository;

import com.pioneerpixel.demo.domain.EmailData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface EmailJpaRepository extends JpaRepository<EmailData, Long> {
    boolean existsByEmail(String email);

    @Modifying
    @Query("delete from EmailData ed where ed.userId = :userId and ed.id = :id")
    int deleteByIdAndUserId(Long id, Long userId);

    @Modifying
    @Query("update EmailData ed set ed.email = :email where ed.userId = :userId and ed.id = :id")
    int update(Long id, Long userId, String email);

    List<EmailData> findAllByUserIdIn(Set<Long> userIds);
}
