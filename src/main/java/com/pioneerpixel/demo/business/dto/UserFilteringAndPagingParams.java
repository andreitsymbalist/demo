package com.pioneerpixel.demo.business.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

@Getter
@Setter
public class UserFilteringAndPagingParams {
    private String name;
    private LocalDate dateOfBirth;
    private String phone;
    private String email;
    private Pageable pageable;
}
