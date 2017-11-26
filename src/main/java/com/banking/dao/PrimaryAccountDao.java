package com.banking.dao;

import com.banking.domain.PrimaryAccount;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PrimaryAccountDao extends PagingAndSortingRepository<PrimaryAccount,Long> {

    /**
     * Find by account number
     *
     * @param accountNumber
     * @return
     */
    PrimaryAccount findByAccountNumber (int accountNumber);
}