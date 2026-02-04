package com.challenge.transactions.service;

import com.challenge.transactions.api.dto.CreateAccountRequest;
import com.challenge.transactions.domain.entity.Account;
import com.challenge.transactions.repository.AccountRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account findById(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new EntityNotFoundException("Account not found with id: " + accountId));
    }

    public Account create(CreateAccountRequest accountRequest) {
        Account account = new Account(
                accountRequest.documentNumber()
        );

        return accountRepository.save(account);
    }

}
