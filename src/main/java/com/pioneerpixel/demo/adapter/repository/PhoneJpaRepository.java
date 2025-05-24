package com.pioneerpixel.demo.adapter.repository;

import com.pioneerpixel.demo.domain.PhoneData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PhoneJpaRepository extends JpaRepository<PhoneData, Long> {
    boolean existsByPhone(String phone);

    @Modifying
    @Query("delete from PhoneData pd where pd.userId = :userId and pd.id = :id")
    int deleteByIdAndUserId(Long id, Long userId);

    @Modifying
    @Query("update PhoneData pd set pd.phone = :phone where pd.userId = :userId and pd.id = :id")
    int update(Long id, Long userId, String phone);

    List<PhoneData> findAllByUserIdIn(Set<Long> userIds);
}
