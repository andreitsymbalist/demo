package com.pioneerpixel.demo.business.schedule;

import com.pioneerpixel.demo.business.api.repository.AccountRepository;
import com.pioneerpixel.demo.business.usecase.InterestAccrualUseCase;
import com.pioneerpixel.demo.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.TimeUnit;


@RequiredArgsConstructor
@Component
public class InterestAccrualScheduledTask {

    private final AccountRepository accountRepository;
    private final InterestAccrualUseCase interestAccrual;

    @Scheduled(timeUnit = TimeUnit.SECONDS, fixedDelay = 30)
    public void execute() {
        accountRepository.getAll().parallelStream().forEach(interestAccrual::execute);
    }
}
