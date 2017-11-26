package com.banking.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

/** The type Appointment */
public @Data class Appointment {

    @Id                                                 // Primary key
    @GeneratedValue(strategy = GenerationType.AUTO)     // Auto increment
    private Long id;
    private Date date;
    private String location;
    private String description;
    private boolean confirmed;
    @ManyToOne                                          // Many appointments to one user
    @JoinColumn(name = "user_id")                       // Join with user_id
    private User user;

}
