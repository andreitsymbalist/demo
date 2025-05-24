package com.pioneerpixel.demo.adapter.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class GetUserRequest {
    private String name;
    private LocalDate dateOfBirth;
    private String phone;
    private String email;
}
