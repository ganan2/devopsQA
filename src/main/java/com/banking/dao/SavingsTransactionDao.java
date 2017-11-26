package com.banking.dao;

import com.banking.domain.SavingsTransaction;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface SavingsTransactionDao extends PagingAndSortingRepository<SavingsTransaction, Long> {

    List<SavingsTransaction> findAll();
}