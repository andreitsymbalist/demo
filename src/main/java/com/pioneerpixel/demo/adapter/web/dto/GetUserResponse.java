package com.pioneerpixel.demo.adapter.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class GetUserResponse {
    private String name;
    private LocalDate dateOfBirth;
    private String password;
    private AccountDto account;
    private List<PhoneDto> phones;
    private List<EmailDto> emails;
}
