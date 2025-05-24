package com.pioneerpixel.demo.adapter.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateEmailRequest {
    @Email
    @NotNull
    private String email;
}
