package com.banking.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

public @Data class SavingsAccount {

    @Id                                             /** Primary key */
    @GeneratedValue(strategy = GenerationType.AUTO) /** Auto increment */
    private Long id;
    private int accountNumber;
    private BigDecimal accountBalance;
    @OneToMany(                                     /** One savings account to many savings transactions. */
            mappedBy = "savingsAccnount",           /** savingsAccount is a property of the class SavingsTransaction. */
            cascade = CascadeType.ALL,              /** The actions on the list will be propagated to the class. */
            fetch = FetchType.LAZY)                 /** When the class object is created, the values from the list need not be retrieved unless required. */
    @JsonIgnore
    private List<SavingsTransaction> savingsTransationList;

    public int getAccountNumber(int accountNumber) {
        return accountNumber;
    }
}
