package com.banking.dao;

import com.banking.domain.SavingsAccount;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SavingsAccountDao extends PagingAndSortingRepository<SavingsAccount, Long> {

    SavingsAccount findByAccountNumber (int accountNumber);
}
