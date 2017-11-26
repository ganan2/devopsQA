package com.banking.service.implementation;

import com.banking.dao.RoleDao;
import com.banking.dao.UserDao;
import com.banking.domain.User;
import com.banking.domain.security.UserRole;
import com.banking.service.AccountService;
import com.banking.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/** The type User service implementation */
@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AccountService accountService;

    /**
     * This method saves user
     *
     * @param user
     */
    public void save(User user) {
        userDao.save(user);
    }

    /**
     * This method finds user by username and returns user
     *
     * @param username
     * @return
     */
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    /**
     * This method finds user by email and returns user
     *
     * @param email
     * @return
     */
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }


    /**
     * The method creates user and returns user
     *
     * @param user
     * @param userRoles
     * @return
     */
    public User createUser(User user, Set<UserRole> userRoles) {
        User localUser = userDao.findByUsername(user.getUsername());

        if (localUser != null) {
            log.info("User with username {} already exist. Nothing will be done. ", user.getUsername());
        } else {
            String encryptedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encryptedPassword);

            for (UserRole ur : userRoles) {
                roleDao.save(ur.getRole());
            }

            user.getUserRoles().addAll(userRoles);

            user.setPrimaryAccount(accountService.createPrimaryAccount());
            user.setSavingsAccount(accountService.createSavingsAccount());

            localUser = userDao.save(user);
        }

        return localUser;
    }

    /**
     * THis method checks if user exists
     *
     * @param username
     * @param email
     * @return
     */
    public boolean checkUserExists(String username, String email){
        if (checkUsernameExists(username) || checkEmailExists(username)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method checks if username exists
     *
     * @param username
     * @return
     */
    public boolean checkUsernameExists(String username) {
        if (null != findByUsername(username)) {
            return true;
        }

        return false;
    }

    /**
     * This method checks if email exists
     *
     * @param email
     * @return
     */
    public boolean checkEmailExists(String email) {
        if (null != findByEmail(email)) {
            return true;
        }

        return false;
    }

    /**
     * This method saves user and returns user
     *
     * @param user
     * @return
     */
    public User saveUser (User user) {
        return userDao.save(user);
    }

    /**
     * This method finds user list
     *
     * @return
     */
    public List<User> findUserList() {
        return userDao.findAll();
    }

    /**
     * This method enables user
     *
     * @param username
     */
    public void enableUser (String username) {
        User user = findByUsername(username);
        user.setEnabled(true);
        userDao.save(user);
    }

    /**
     * This method disables user
     *
     * @param username
     */
    public void disableUser (String username) {
        User user = findByUsername(username);
        user.setEnabled(false);
        System.out.println(user.isEnabled());
        userDao.save(user);
        System.out.println(username + " is disabled.");
    }
}



