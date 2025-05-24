package com.pioneerpixel.demo.business.usecase;

import com.pioneerpixel.demo.business.api.repository.AccountRepository;
import com.pioneerpixel.demo.business.api.security.AuthenticationManager;
import com.pioneerpixel.demo.domain.Account;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Component
public class TransferMoneyUserCase {

    private final AccountRepository accountRepository;
    private final AuthenticationManager authenticationManager;

    @Retryable(retryFor = ObjectOptimisticLockingFailureException.class)
    @Transactional
    public void execute(Long targetUserId, BigDecimal value) {
        if (value.compareTo(new BigDecimal(3)) < 0) {
            throw new IllegalArgumentException("Minimum transfer must equal to or greater than 3");
        }

        Long sourceUserId = authenticationManager.getUserId();
        Account source = accountRepository.get(sourceUserId);
        BigDecimal sourceBalance = source.getBalance();
        if (sourceBalance.compareTo(value) < 0) {
            throw new IllegalArgumentException("Not enough money");
        }

        source.setBalance(sourceBalance.subtract(value));
        accountRepository.update(source);

        Account target = accountRepository.get(targetUserId);
        target.setBalance(target.getBalance().add(value));
        accountRepository.update(target);
    }
}
