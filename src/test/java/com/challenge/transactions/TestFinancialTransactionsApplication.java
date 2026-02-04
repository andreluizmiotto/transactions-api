package com.challenge.transactions;

import org.springframework.boot.SpringApplication;

public class TestFinancialTransactionsApplication {

    public static void main(String[] args) {
        SpringApplication.from(FinancialTransactionsApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
