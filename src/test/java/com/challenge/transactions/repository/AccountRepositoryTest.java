package com.challenge.transactions.repository;

import com.challenge.transactions.domain.entity.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

@DataJpaTest
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    @DisplayName("Should throw DataIntegrityViolationException when document number is duplicated")
    void shouldThrowExceptionWhenDocumentNumberIsDuplicated() {
        String document = "123";

        Account account1 = new Account(document);
        accountRepository.save(account1);
        accountRepository.flush();

        Account account2 = new Account(document);

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            accountRepository.save(account2);
            accountRepository.flush();
        });
    }

}