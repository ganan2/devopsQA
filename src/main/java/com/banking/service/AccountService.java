package com.banking.service;

import com.banking.domain.PrimaryAccount;
import com.banking.domain.SavingsAccount;

import java.security.Principal;

/** THe type account service */
public interface AccountService {

    /**
     * Create primary account
     *
     * @return
     */
    PrimaryAccount createPrimaryAccount();

    /**
     * Create savings account
     *
     * @return
     */
    SavingsAccount createSavingsAccount();

    /**
     * Deposit amount
     *
     * @param accountType
     * @param amount
     * @param principal
     */
    void deposit(String accountType, double amount, Principal principal);

    /**
     * Withdraw amount
     *
     * @param accountType
     * @param amount
     * @param principal
     */
    void withdraw(String accountType, double amount, Principal principal);

}