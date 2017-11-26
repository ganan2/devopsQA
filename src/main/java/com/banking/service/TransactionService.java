package com.banking.service;

import com.banking.domain.PrimaryAccount;
import com.banking.domain.PrimaryTransaction;
import com.banking.domain.SavingsAccount;
import com.banking.domain.SavingsTransaction;

import java.util.List;

public interface TransactionService {

    List<PrimaryTransaction> findPrimaryTransactionList(String username);

    List<SavingsTransaction> findSavingsTransactionList(String username);

    void savePrimaryDepositTransaction(PrimaryTransaction primaryTransaction);

    void saveSavingsDepositTransaction(SavingsTransaction savingsTransaction);

    void betweenAccountsTransfer(String transferFrom,
                                 String transferTo,
                                 String amount,
                                 PrimaryAccount primaryAccount,
                                 SavingsAccount savingsAccount) throws Exception;

}
