package com.banking.service;

import com.banking.domain.User;
import com.banking.domain.security.UserRole;

import java.util.List;
import java.util.Set;

/** The type User service */
public interface UserService {

    /**
     * Find user name
     *
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * Find by email
     *
     * @param email
     * @return
     */
    User findByEmail(String email);

    /**
     * Check if user exists
     *
     * @param username
     * @param email
     * @return
     */
    boolean checkUserExists(String username, String email);

    /**
     * Check if user name exists
     * @param username
     * @return
     */
    boolean checkUsernameExists(String username);

    /**
     * Check if email exists
     *
     * @param email
     * @return
     */
    boolean checkEmailExists(String email);

    /**
     * Save user
     *
     * @param user
     */
    void save (User user);

    /**
     * Create user
     *
     * @param user
     * @param userRoles
     * @return
     */
    User createUser(User user, Set<UserRole> userRoles);

    /**
     * Save user
     *
     * @param user
     * @return
     */
    User saveUser (User user);

    /**
     * Find user list
     *
     * @return
     */
    List<User> findUserList();

    /**
     * Enable user
     *
     * @param username
     */
    void enableUser (String username);

    /**
     * Disable user
     *
     * @param username
     */
    void disableUser (String username);

}
