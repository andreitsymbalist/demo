package com.pioneerpixel.demo.adapter.repository;

import com.pioneerpixel.demo.adapter.repository.specification.UserSpecificationBuilder;
import com.pioneerpixel.demo.business.api.repository.UserRepository;
import com.pioneerpixel.demo.business.dto.UserFilteringAndPagingParams;
import com.pioneerpixel.demo.domain.EmailData;
import com.pioneerpixel.demo.domain.PhoneData;
import com.pioneerpixel.demo.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class UserRepositoryAdapter implements UserRepository {

    private final UserJpaRepository userRepository;
    private final PhoneJpaRepository phoneRepository;
    private final EmailJpaRepository emailRepository;

    @Override
    public List<User> getAll(UserFilteringAndPagingParams filter) {
        Specification<User> specification = UserSpecificationBuilder.builder(true)
            .nameStartsWith(filter.getName())
            .dateOfBirthAfter(filter.getDateOfBirth())
            .emailEquals(filter.getEmail())
            .phoneEquals(filter.getPhone())
            .build();
        Map<Long, User> idToUser = userRepository.findAll(specification, filter.getPageable())
            .stream().collect(Collectors.toMap(User::getId, Function.identity()));
        fetchPhones(idToUser);
        fetchEmails(idToUser);
        return idToUser.values().stream().toList();
    }

    private void fetchPhones(Map<Long, User> idToUser) {
        phoneRepository.findAllByUserIdIn(idToUser.keySet()).stream()
            .collect(Collectors.groupingBy(PhoneData::getUserId))
            .forEach((userId, phones) -> idToUser.get(userId).setPhones(phones));
    }

    private void fetchEmails(Map<Long, User> idToUser) {
        emailRepository.findAllByUserIdIn(idToUser.keySet()).stream()
            .collect(Collectors.groupingBy(EmailData::getUserId))
            .forEach((userId, emails) -> idToUser.get(userId).setEmails(emails));
    }

    @Override
    public Optional<User> get(String password, String phone) {
        return userRepository.findByPasswordAndPhonesPhone(password, phone);
    }
}
