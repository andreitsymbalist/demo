package com.pioneerpixel.demo.adapter.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePhoneRequest {
    @NotNull
    private String phone;
}
