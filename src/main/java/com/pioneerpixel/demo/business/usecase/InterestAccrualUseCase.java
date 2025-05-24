package com.pioneerpixel.demo.business.usecase;

import com.pioneerpixel.demo.business.api.repository.AccountRepository;
import com.pioneerpixel.demo.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@RequiredArgsConstructor
@Component
public class InterestAccrualUseCase {

    private static final BigDecimal ONE_HUNDRED = BigDecimal.valueOf(100);
    private static final MathContext MATH_CONTEXT = new MathContext(20, RoundingMode.HALF_UP);

    private final AccountRepository accountRepository;

    @Value("${deposit.interest}")
    private BigDecimal interest;
    @Value("${deposit.max-interest}")
    private BigDecimal maxInterest;

    @Retryable(retryFor = ObjectOptimisticLockingFailureException.class)
    public void execute(Account account) {
        BigDecimal initialBalance = account.getInitialBalance();
        BigDecimal maxValue = initialBalance.divide(ONE_HUNDRED, MATH_CONTEXT).multiply(maxInterest);
        BigDecimal balance = account.getBalance();
        if (balance.compareTo(maxValue) >= 0) {
            return;
        }
        BigDecimal tenPercent = balance.divide(ONE_HUNDRED, MATH_CONTEXT).multiply(interest);
        BigDecimal result = balance.add(tenPercent);
        result = result.compareTo(maxValue) > 0 ? maxValue : result;
        account.setBalance(result);
        accountRepository.update(account);
    }
}
