package com.challenge.transactions.api.controller;

import com.challenge.transactions.api.dto.AccountResponse;
import com.challenge.transactions.api.dto.CreateAccountRequest;
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
    public AccountResponse createAccount(@RequestBody @Valid CreateAccountRequest accountRequest) {
        return AccountResponse.fromAccount(
                accountService.create(accountRequest)
        );
    }

    @GetMapping("/{accountId}")
    public AccountResponse getAccount(@PathVariable Long accountId) {
        return AccountResponse.fromAccount(
                accountService.findById(accountId)
        );
    }

}
