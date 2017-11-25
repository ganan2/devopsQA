package com.banking.domain;

import lombok.Data;

import java.util.Date;

public @Data class Appointment {
    private Long id;
    private Date date;
    private String location;
    private String description;
    private boolean confirmed;
    private User user;
}
