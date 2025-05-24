package com.pioneerpixel.demo.business.api.repository;

import com.pioneerpixel.demo.domain.Account;

import java.util.List;

public interface AccountRepository {
    List<Account> getAll();

    Account update(Account account);

    Account get(Long userId);
}
