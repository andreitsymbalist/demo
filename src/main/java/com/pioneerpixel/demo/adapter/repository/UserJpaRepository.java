package com.pioneerpixel.demo.adapter.repository;

import com.pioneerpixel.demo.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    Optional<User> findByPasswordAndPhonesPhone(String password, String phone);

    @EntityGraph(attributePaths = {"account"})
    @Override
    Page<User> findAll(@Nullable Specification<User> spec, Pageable pageable);
}
