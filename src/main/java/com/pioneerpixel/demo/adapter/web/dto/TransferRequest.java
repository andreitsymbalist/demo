package com.pioneerpixel.demo.adapter.web.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransferRequest {
    @NotNull
    private Long targetId;
    @Min(3)
    @NotNull
    private BigDecimal value;
}
