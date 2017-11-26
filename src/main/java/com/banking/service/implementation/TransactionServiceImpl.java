package com.banking.service.implementation;

import com.banking.dao.*;
import com.banking.domain.*;
import com.banking.service.TransactionService;
import com.banking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/** The type Transaction service implementaton */
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private UserService userService;

    @Autowired
    private PrimaryTransactionDao primaryTransactionDao;

    @Autowired
    private SavingsTransactionDao savingsTransactionDao;

    @Autowired
    private PrimaryAccountDao primaryAccountDao;

    @Autowired
    private SavingsAccountDao savingsAccountDao;

    @Autowired
    private RecipientDao recipientDao;

    /**
     * Returns user
     *
     * @param username
     * @return
     */
    @Override
    public List<PrimaryTransaction> findPrimaryTransactionList(String username) {
        User user = userService.findByUsername(username);
        List<PrimaryTransaction> primaryTransactionList = user.getPrimaryAccount().getPrimaryTransactionList();
        return primaryTransactionList;
    }

    /**
     * Returns Savings transaction list
     *
     * @param username
     * @return
     */
    @Override
    public List<SavingsTransaction> findSavingsTransactionList(String username) {
        User user = userService.findByUsername(username);
        List<SavingsTransaction> savingsTransactionList = user.getSavingsAccount().getSavingsTransationList();
        return savingsTransactionList;
    }

    /**
     * Saves primary transaction
     *
     * @param primaryTransaction
     */
    @Override
    public void savePrimaryDepositTransaction(PrimaryTransaction primaryTransaction) {
        primaryTransactionDao.save(primaryTransaction);
    }

    /**
     * Saves savings transaction
     *
     * @param savingsTransaction
     */
    @Override
    public void saveSavingsDepositTransaction(SavingsTransaction savingsTransaction) {
        savingsTransactionDao.save(savingsTransaction);
    }

    /**
     * Services transfer between primary account and savings account
     *
     * @param transferFrom
     * @param transferTo
     * @param amount
     * @param primaryAccount
     * @param savingsAccount
     * @throws Exception
     */
    public void betweenAccountsTransfer(
            String transferFrom,
            String transferTo,
            String amount,
            PrimaryAccount primaryAccount,
            SavingsAccount savingsAccount) throws Exception {

        if (transferFrom.equalsIgnoreCase("Primary") && transferTo.equalsIgnoreCase("Savings")) {
            primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().subtract(new BigDecimal(amount)));
            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().add(new BigDecimal(amount)));
            primaryAccountDao.save(primaryAccount);
            savingsAccountDao.save(savingsAccount);

            Date date = new Date();

            PrimaryTransaction primaryTransaction =
                    new PrimaryTransaction(
                            date,
                            "Between account transfer from "+transferFrom+" to "+transferTo,
                            "Account",
                            "Finished",
                            Double.parseDouble(amount),
                            primaryAccount.getAccountBalance(),
                            primaryAccount);

            primaryTransactionDao.save(primaryTransaction);
        } else if (transferFrom.equalsIgnoreCase("Savings") && transferTo.equalsIgnoreCase("Primary")) {
            primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().add(new BigDecimal(amount)));
            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().subtract(new BigDecimal(amount)));
            primaryAccountDao.save(primaryAccount);
            savingsAccountDao.save(savingsAccount);

            Date date = new Date();

            SavingsTransaction savingsTransaction =
                    new SavingsTransaction(
                            date,
                            "Between account transfer from "+transferFrom+" to "+transferTo,
                            "Transfer",
                            "Finished",
                            Double.parseDouble(amount),
                            savingsAccount.getAccountBalance(),
                            savingsAccount);

            savingsTransactionDao.save(savingsTransaction);
        } else {
            throw new Exception("Invalid Transfer");
        }
    }

    /**
     * Finds recipient list
     *
     * @param principal
     * @return
     */
    public List<Recipient> findRecipientList(Principal principal) {
        String username = principal.getName();
        List<Recipient> recipientList = recipientDao.findAll().stream() 			//convert list to stream
                .filter(recipient -> username.equals(recipient.getUser().getUsername()))	//filters the line, equals to username
                .collect(Collectors.toList());

        return recipientList;
    }

    /**
     * Saves recipient
     *
     * @param recipient
     * @return
     */
    public Recipient saveRecipient(Recipient recipient) {
        return recipientDao.save(recipient);
    }

    /**
     * Finds recipient
     *
     * @param recipientName
     * @return
     */
    public Recipient findRecipientByName(String recipientName) {
        return recipientDao.findByName(recipientName);
    }

    /**
     * Deletes recipient
     *
     * @param recipientName
     */
    public void deleteRecipientByName(String recipientName) {
        recipientDao.deleteByName(recipientName);
    }



}
