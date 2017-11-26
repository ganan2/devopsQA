package com.banking.dao;

import com.banking.domain.PrimaryTransaction;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PrimaryTransactionDao extends PagingAndSortingRepository<PrimaryTransaction, Long> {

    /**
     * Find all
     *
     * @return
     */
    List<PrimaryTransaction> findAll();
}