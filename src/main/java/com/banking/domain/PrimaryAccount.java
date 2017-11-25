package com.banking.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

public @Data class PrimaryAccount {

    @Id                                             /** Primary key */
    @GeneratedValue(strategy = GenerationType.AUTO) /** Auto increment */
    private Long id;
    private int accountNumber;
    private BigDecimal accountBalance;
    @OneToMany(                                     /** One primary account to many primary transactions. */
            mappedBy = "primaryAccount",            /** THe class PrimaryTransaction has a property primaryAccount. */
            cascade = CascadeType.ALL,              /** Actions on the list will propagated to the class. */
            fetch = FetchType.LAZY)                 /** When the class object is created, the values from the list need not be retrieved unless required. */
    private List<PrimaryTransaction> primaryTransactionList;
}
