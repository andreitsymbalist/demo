package com.pioneerpixel.demo.adapter.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor(staticName = "of")
public class LoginResponse {
    private String jwt;
}
