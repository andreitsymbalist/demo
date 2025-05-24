package com.pioneerpixel.demo.adapter.repository;

import com.pioneerpixel.demo.business.api.repository.AccountRepository;
import com.pioneerpixel.demo.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class AccountRepositoryAdapter implements AccountRepository {

    private final AccountJpaRepository repository;

    @Override
    public List<Account> getAll() {
        return repository.findAll();
    }

    @CachePut(value = "accounts", key = "#account.user.id")
    @Override
    public Account update(Account account) {
        return repository.save(account);
    }

    @Cacheable(value = "accounts", key = "#userId")
    @Override
    public Account get(Long userId) {
        return repository.findByUserId(userId);
    }
}
