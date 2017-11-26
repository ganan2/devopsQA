package com.banking.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/** The type Recipient */
public @Data class Recipient {

    @Id                                             // Primary Key
    @GeneratedValue(strategy = GenerationType.AUTO) // Auto increment
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String accountNumber;
    private String description;

    @ManyToOne                                      // Many recipients to one user
    @JoinColumn(name = "user_id")                   // Join column user_id
    @JsonIgnore                                     // Do not show in the response
    private User user;

}
