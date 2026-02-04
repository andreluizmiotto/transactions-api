package com.challenge.transactions.api.controller;

import com.challenge.transactions.api.dto.CreateAccountRequest;
import com.challenge.transactions.domain.entity.Account;
import com.challenge.transactions.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public Account createAccount(@RequestBody @Valid CreateAccountRequest accountRequest) {
        return accountService.create(accountRequest);
    }

    @GetMapping("/{accountId}")
    public Account getAccount(@PathVariable Long accountId) {
        return accountService.findById(accountId);
    }

}
