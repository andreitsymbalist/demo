package com.pioneerpixel.demo.business.api.repository;

import com.pioneerpixel.demo.business.dto.UserFilteringAndPagingParams;
import com.pioneerpixel.demo.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> getAll(UserFilteringAndPagingParams filter);

    Optional<User> get(String password, String phone);
}
