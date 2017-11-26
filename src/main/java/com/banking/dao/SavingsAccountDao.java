package com.banking.dao;

import com.banking.domain.SavingsAccount;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SavingsAccountDao extends PagingAndSortingRepository<SavingsAccount, Long> {

    /**
     * Find by account number
     *
     * @param accountNumber
     * @return
     */
    SavingsAccount findByAccountNumber (int accountNumber);
}
