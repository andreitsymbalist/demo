package com.pioneerpixel.demo.adapter.web.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    @NotNull
    private String phone;
    @Size(min = 8, max = 500)
    private String password;
}
