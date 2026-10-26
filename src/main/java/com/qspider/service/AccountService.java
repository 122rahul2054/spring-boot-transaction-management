package com.qspider.service;

import com.qspider.entity.Account;
import com.qspider.repository.AccountRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public Account createAccount(Account account) {

        if (account.getHolderName() == null
                || account.getHolderName().isBlank()) {
            throw new IllegalArgumentException(
                    "Account holder name is required");
        }

        if (account.getBalance() == null
                || account.getBalance().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(
                    "Balance cannot be negative or null");
        }

        return accountRepository.save(account);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Account not found: " + id));
    }
}