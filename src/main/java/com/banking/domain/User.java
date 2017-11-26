package com.banking.domain;

import com.banking.domain.security.Authority;
import com.banking.domain.security.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** The type User */
public @Data class User implements UserDetails{

    @Id                                             // Primary account
    @GeneratedValue(strategy = GenerationType.AUTO) // Auto increment
    private Long userID;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    @Column(
            name = "email",                         // The field email in the database
            nullable = false,                       // Not null
            unique = true)                          // Unique
    private String email;
    private String phone;
    private boolean enabled = true;
    @OneToOne                                       // One user to one primary account
    private PrimaryAccount primaryAccount;
    @OneToOne                                       // One user to one savings account
    private SavingsAccount savingsAccount;
    @OneToMany(                                     // One user to many primary accounts
            mappedBy = "user",                      // user is a property of the class Appointment
            cascade = CascadeType.ALL,              // The actions on list will be propagated to the class
            fetch = FetchType.LAZY)                 // When an object is created, the values from the object need to be retrieved unless required
    private List<Appointment> appointmentList;
    @OneToMany(                                     // One user to many primary accounts
            mappedBy = "user",                      // user is a property of the class Recipient
            cascade = CascadeType.ALL,              // The actions on list will be propagated to the class
            fetch = FetchType.LAZY)                 // When an object is created, the values from the object need to be retrieved unless required
    private List<Recipient> recipientList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<UserRole> userRoles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        userRoles.forEach(ur -> authorities.add(new Authority(ur.getRole().getName())));
        return authorities;
    }

    /**
     * Check if account expired
     *
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    /**
     * Check if account locked
     *
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    /**
     * Check if credentials expired
     *
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }
}
