package com.qspider.service;

import com.qspider.entity.Account;
import com.qspider.entity.TransferRecord;
import com.qspider.repository.AccountRepository;
import com.qspider.repository.TransferRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TransferService {

    private final AccountRepository accountRepository;
    private final TransferRepository transferRepository;

    public TransferService(AccountRepository accountRepository,
                           TransferRepository transferRepository) {
        this.accountRepository = accountRepository;
        this.transferRepository = transferRepository;
    }

    @Transactional
    public TransferRecord transfer(Long fromAccountId,
                                   Long toAccountId,
                                   BigDecimal amount) {

        if (fromAccountId == null || toAccountId == null) {
            throw new IllegalArgumentException(
                    "Both account IDs are required");
        }

        if (fromAccountId.equals(toAccountId)) {
            throw new IllegalArgumentException(
                    "Source and destination accounts must differ");
        }

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "Transfer amount must be greater than zero");
        }

        Account fromAccount = accountRepository.findById(fromAccountId)
                .orElseThrow(() ->
                        new RuntimeException("Source account not found"));

        Account toAccount = accountRepository.findById(toAccountId)
                .orElseThrow(() ->
                        new RuntimeException("Destination account not found"));

        // Deduct money from the source account.
        fromAccount.debitAccount(amount);

        // Add money to the destination account.
        toAccount.creditAccount(amount);

        // Save the transfer history.
        TransferRecord record = new TransferRecord(
                fromAccountId,
                toAccountId,
                amount,
                LocalDateTime.now()
        );

        return transferRepository.save(record);
    }
}