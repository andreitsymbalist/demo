package com.pioneerpixel.demo.adapter.web.dto;

import com.pioneerpixel.demo.domain.Account;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetAccountsResponse {
    private List<Account> accounts;
}
