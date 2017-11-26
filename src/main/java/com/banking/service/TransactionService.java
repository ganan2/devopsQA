package com.banking.service;

import com.banking.domain.*;

import java.security.Principal;
import java.util.List;

/** The type Transaction service */
public interface TransactionService {

    /**
     * Find primary transaction list
     *
     * @param username
     * @return
     */
    List<PrimaryTransaction> findPrimaryTransactionList(String username);

    /**
     * Find savings transaction list
     *
     * @param username
     * @return
     */
    List<SavingsTransaction> findSavingsTransactionList(String username);

    /**
     * Save primary deposit transaction
     *
     * @param primaryTransaction
     */
    void savePrimaryDepositTransaction(PrimaryTransaction primaryTransaction);

    /**
     * Save savings deposit transaction
     *
     * @param savingsTransaction
     */
    void saveSavingsDepositTransaction(SavingsTransaction savingsTransaction);

    /**
     * Transfer between accounts
     *
     * @param transferFrom
     * @param transferTo
     * @param amount
     * @param primaryAccount
     * @param savingsAccount
     * @throws Exception
     */
    void betweenAccountsTransfer(String transferFrom,
                                 String transferTo,
                                 String amount,
                                 PrimaryAccount primaryAccount,
                                 SavingsAccount savingsAccount) throws Exception;

    /**
     * Find recipient list
     *
     * @param principal
     * @return
     */
    List<Recipient> findRecipientList(Principal principal);

    /**
     * Save recipient
     *
     * @param recipient
     * @return
     */
    Recipient saveRecipient(Recipient recipient);

    /**
     * Find recipient
     *
     * @param recipientName
     * @return
     */
    Recipient findRecipientByName(String recipientName);

    /**
     * Delete recipient
     *
     * @param recipientName
     */
    void deleteRecipientByName(String recipientName);

}
