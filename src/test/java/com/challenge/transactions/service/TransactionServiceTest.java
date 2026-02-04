package com.challenge.transactions.service;

import com.challenge.transactions.api.dto.CreateTransactionRequest;
import com.challenge.transactions.domain.entity.Account;
import com.challenge.transactions.domain.entity.Transaction;
import com.challenge.transactions.repository.TransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AccountService accountService;

    @InjectMocks
    private TransactionService transactionService;

    @Captor
    private ArgumentCaptor<Transaction> transactionCaptor;

    @Test
    @DisplayName("Should create generic purchase transaction with NEGATIVE value")
    void shouldCreatePurchaseWithNegativeValue() {
        Long accountId = 1L;
        CreateTransactionRequest request = new CreateTransactionRequest(
                accountId,
                1,
                new BigDecimal("50.00")
        );

        Account account = new Account("12345678900");
        account.setId(accountId);

        Mockito.when(accountService.findById(accountId)).thenReturn(account);

        transactionService.create(request);

        Mockito.verify(transactionRepository).save(transactionCaptor.capture());
        Transaction savedTransaction = transactionCaptor.getValue();

        Assertions.assertEquals(new BigDecimal("-50.00"), savedTransaction.getAmount());
        Assertions.assertEquals(1, savedTransaction.getOperationType());
        Assertions.assertNotNull(savedTransaction.getEventDate());
    }

    @Test
    @DisplayName("Should create credit voucher transaction with POSITIVE value")
    void shouldCreateCreditVoucherWithPositiveValue() {
        Long accountId = 1L;
        CreateTransactionRequest request = new CreateTransactionRequest(
                accountId,
                4,
                new BigDecimal("100.00")
        );

        Account account = new Account("12345678900");
        Mockito.when(accountService.findById(accountId)).thenReturn(account);

        transactionService.create(request);

        Mockito.verify(transactionRepository).save(transactionCaptor.capture());
        Transaction savedTransaction = transactionCaptor.getValue();

        Assertions.assertEquals(new BigDecimal("100.00"), savedTransaction.getAmount());
        Assertions.assertEquals(4, savedTransaction.getOperationType());
    }

    @Test
    @DisplayName("Should handle negative input for purchase ensuring it remains negative")
    void shouldHandleNegativeInputForPurchase() {
        CreateTransactionRequest request = new CreateTransactionRequest(
                1L,
                3,
                new BigDecimal("-23.50")
        );

        Account account = new Account("123");
        Mockito.when(accountService.findById(1L)).thenReturn(account);

        transactionService.create(request);

        Mockito.verify(transactionRepository).save(transactionCaptor.capture());
        Transaction savedTransaction = transactionCaptor.getValue();

        Assertions.assertEquals(new BigDecimal("-23.50"), savedTransaction.getAmount());
    }

    @Test
    @DisplayName("Should throw exception when Operation Type is invalid")
    void shouldThrowExceptionInvalidOperationType() {
        CreateTransactionRequest request = new CreateTransactionRequest(
                1L,
                99,
                BigDecimal.TEN
        );

        Mockito.when(accountService.findById(1L)).thenReturn(new Account());

        Assertions.assertThrows(IllegalArgumentException.class, () -> transactionService.create(request));

        Mockito.verifyNoInteractions(transactionRepository);
    }



}