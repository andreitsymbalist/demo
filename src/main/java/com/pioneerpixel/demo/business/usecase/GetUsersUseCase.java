package com.pioneerpixel.demo.business.usecase;

import com.pioneerpixel.demo.business.api.repository.UserRepository;
import com.pioneerpixel.demo.business.dto.UserFilteringAndPagingParams;
import com.pioneerpixel.demo.domain.PhoneData;
import com.pioneerpixel.demo.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class GetUsersUseCase {

    private final UserRepository userRepository;

    public List<User> execute(UserFilteringAndPagingParams dto) {
        return userRepository.getAll(dto);
    }
}
