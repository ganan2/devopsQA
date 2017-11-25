package com.banking.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

public @Data
class PrimaryAccount {
    private Long id;
    private int accountNumber;
    private BigDecimal accountBalance;
    private List<PrimaryTransaction> primaryTransactionList;
}
