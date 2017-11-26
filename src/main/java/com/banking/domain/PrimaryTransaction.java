package com.banking.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.Date;

/** The type Primary transaction */
public @Data class PrimaryTransaction {

    @Id                                                 // Primary key
    @GeneratedValue(strategy = GenerationType.AUTO)     // Auto increment
    private Long id;
    private Date date;
    private String description;
    private String type;
    private String status;
    private double amount;
    private BigDecimal availableBalance;
    @ManyToOne                                          // Many primary transaction to one primary account.
    @JoinColumn(name = "primaryAccount")                // Primary account is a column in PrimaryTransaction table. Join primary account for each primary transaction.
    private PrimaryAccount primaryAccount;              // Each primary account section shows primary transactions which includes primaryAccount with each transaction.

    /**
     * Instantiates primary transaction
     *
     * @param date              The transaction date
     * @param description       The transaction description
     * @param type              The transaction type
     * @param status            The transaction status
     * @param amount            The transaction amount
     * @param availableBalance  The available balance
     * @param primaryAccount    The primary account
     */
    public PrimaryTransaction(Date date,
                              String description,
                              String type,
                              String status,
                              double amount,
                              BigDecimal availableBalance,
                              PrimaryAccount primaryAccount) {
        this.date = date;
        this.description = description;
        this.type = type;
        this.status = status;
        this.amount = amount;
        this.availableBalance = availableBalance;
        this.primaryAccount = primaryAccount;
    }
}
