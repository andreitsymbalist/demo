package com.pioneerpixel.demo.adapter.web;

import com.pioneerpixel.demo.adapter.web.dto.TransferRequest;
import com.pioneerpixel.demo.business.usecase.TransferMoneyUserCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/transfers")
public class TransferController {

    private final TransferMoneyUserCase transferMoney;

    @PostMapping
    public void transfer(@RequestBody @Valid TransferRequest request) {
        transferMoney.execute(request.getTargetId(), request.getValue());
    }
}
