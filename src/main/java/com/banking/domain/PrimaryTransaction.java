package com.banking.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

public @Data class PrimaryTransaction {
    private Long id;
    private Date date;
    private String description;
    private String type;
    private String status;
    private double amount;
    private BigDecimal availableBalance;
    private PrimaryAccount primaryAccount;

    public PrimaryTransaction(){

    }
}
