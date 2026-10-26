package com.qspider.controller;

import com.qspider.entity.TransferRecord;
import com.qspider.service.TransferService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/api/transfers")
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    public ResponseEntity<?> transfer(
            @RequestBody Map<String, Object> request) {

        try {
            Long fromAccountId =
                    Long.valueOf(request.get("fromAccountId").toString());

            Long toAccountId =
                    Long.valueOf(request.get("toAccountId").toString());

            BigDecimal amount =
                    new BigDecimal(request.get("amount").toString());

            TransferRecord record = transferService.transfer(
                    fromAccountId, toAccountId, amount);

            return ResponseEntity.ok(record);

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error",
                            e.getMessage() == null
                                    ? "Invalid transfer request"
                                    : e.getMessage()));
        }
    }
}