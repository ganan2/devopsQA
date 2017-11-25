package com.banking.domain;

import lombok.Data;


public @Data class User {

    private Long userID;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

}
