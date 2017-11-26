package com.banking.service.implementation;

import com.banking.dao.PrimaryAccountDao;
import com.banking.dao.SavingsAccountDao;
import com.banking.domain.PrimaryAccount;
import com.banking.domain.SavingsAccount;
import com.banking.service.AccountService;
import com.banking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.security.Principal;

import static com.banking.serviceUtils.serviceConstants.ServiceConstants.NEXT_ACCOUNT_NUMBER;

public class AccountServiceImpl implements AccountService{

    private static int nextAccountNumber = NEXT_ACCOUNT_NUMBER;

    @Autowired
    private PrimaryAccountDao primaryAccountDao;

    @Autowired
    private SavingsAccountDao savingsAccountDao;

    @Autowired
    private UserService userService;

    public PrimaryAccount createPrimaryAccount() {
        PrimaryAccount primaryAccount = new PrimaryAccount();
        primaryAccount.setAccountBalance(new BigDecimal(0.0));
        primaryAccount.getAccountNumber(accountGen());
        primaryAccountDao.save(primaryAccount);

        return primaryAccountDao.findByAccountNumber(primaryAccount.getAccountNumber());
    }

    public SavingsAccount createSavingsAccount() {
        SavingsAccount savingsAccount = new SavingsAccount();
        savingsAccount.setAccountBalance(new BigDecimal(0.0));
        savingsAccount.getAccountNumber(accountGen());
        savingsAccountDao.save(savingsAccount);

        return savingsAccountDao.findByAccountNumber(savingsAccount.getAccountNumber());
    }

    public void deposit(String accountType, double amount, Principal principal) {

    }

    public void withdraw(String accountType, double amount, Principal principal) {

    }

    private int accountGen(){
        return ++nextAccountNumber;
    }
}
