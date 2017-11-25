package com.banking.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.List;


public @Data class User {

    @Id                                             /** Primary account */
    @GeneratedValue(strategy = GenerationType.AUTO) /** Auto increment */
    private Long userID;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    @Column(
            name = "email",                         /** The field email in the database */
            nullable = false,                       /** Not null */
            unique = true)                          /** Unique */
    private String email;
    private String phone;
    private boolean enabled = true;
    @OneToOne                                       /** One user to one primary account */
    private PrimaryAccount primaryAccount;
    @OneToOne                                       /** One user to one savings account */
    private SavingsAccount savingsAccount;
    @OneToMany(                                     /** One user to many primary accounts */
            mappedBy = "user",                      /** user is a property of the class Appointment */
            cascade = CascadeType.ALL,              /** The actions on list will be propagated to the class */
            fetch = FetchType.LAZY)                 /** When an object is created, the values from the object need to be retrieved unless required */
    private List<Appointment> appointmentList;
    @OneToMany(                                     /** One user to many primary accounts */
            mappedBy = "user",                      /** user is a property of the class Recipient */
            cascade = CascadeType.ALL,              /** The actions on list will be propagated to the class */
            fetch = FetchType.LAZY)                 /** When an object is created, the values from the object need to be retrieved unless required */
    private List<Recipient> recipientList;

}
