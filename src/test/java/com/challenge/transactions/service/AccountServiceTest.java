package com.challenge.transactions.service;

import com.challenge.transactions.api.dto.CreateAccountRequest;
import com.challenge.transactions.domain.entity.Account;
import com.challenge.transactions.repository.AccountRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    @Test
    @DisplayName("Should create account successfully")
    void shouldCreateAccountSuccessfully() {
        CreateAccountRequest createAccountRequest = new CreateAccountRequest("12345678900");
        Account expectedAccount = new Account("12345678900");
        expectedAccount.setId(1L);

        Mockito.when(accountRepository.save(Mockito.any(Account.class))).thenReturn(expectedAccount);

        Account result = accountService.create(createAccountRequest);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1L, result.getId());
        Assertions.assertEquals(expectedAccount.getDocumentNumber(), result.getDocumentNumber());

        Mockito.verify(accountRepository, Mockito.times(1)).save(Mockito.any(Account.class));
    }

    @Test
    @DisplayName("Should find account by ID successfully")
    void shouldFindAccountById() {
        Long accountId = 1L;
        Account account = new Account("12345678900");
        Mockito.when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));

        Account result = accountService.findById(accountId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(account.getDocumentNumber(), result.getDocumentNumber());
    }

    @Test
    @DisplayName("Should throw exception when account not found")
    void shouldThrowExceptionWhenAccountNotFound() {
        Long accountId = 99L;
        Mockito.when(accountRepository.findById(accountId)).thenReturn(Optional.empty());

        EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class, () -> accountService.findById(accountId));

        Assertions.assertEquals("Account not found with id: 99", exception.getMessage());
    }

}