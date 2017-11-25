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

    public SavingsTransaction(){

    }
}
