package com.banking.dao;

import com.banking.domain.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UserDao extends PagingAndSortingRepository<User, Long>{

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
     * Find all
     * @return
     */
    List<User> findAll();
}
