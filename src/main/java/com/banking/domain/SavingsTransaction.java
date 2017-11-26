package com.banking.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.Date;

public @Data class SavingsTransaction {

    @Id                                                 /** Primary key */
    @GeneratedValue (strategy = GenerationType.AUTO)    /** Auto increment */
    private Long id;
    private Date date;
    private String description;
    private String type;
    private String status;
    private double amount;
    private BigDecimal availableBalance;
    @ManyToOne                                          /** Many savings transactions to one savings account. */
    @JoinColumn(name ="savings_account_id")             /** Join with the field savings_account_id */
    private SavingsAccount savingsAccount;

    /**
     * Instantiates savings transaction
     *
     * @param date              The transaction date
     * @param description       The transaction description
     * @param type              The transaction type
     * @param status            The transaction status
     * @param amount            The transaction amount
     * @param availableBalance  The available balance
     * @param savingsAccount    The primary account
     */
    public SavingsTransaction(Date date,
                              String description,
                              String type,
                              String status,
                              double amount,
                              BigDecimal availableBalance,
                              SavingsAccount savingsAccount) {
        this.date = date;
        this.description = description;
        this.type = type;
        this.status = status;
        this.amount = amount;
        this.availableBalance = availableBalance;
        this.savingsAccount = savingsAccount;
    }
}
