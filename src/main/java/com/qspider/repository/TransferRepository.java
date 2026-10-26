package com.qspider.repository;

import com.qspider.entity.TransferRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository
        extends JpaRepository<TransferRecord, Long> {
}