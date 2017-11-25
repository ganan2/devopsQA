package com.banking.dao;

import com.banking.domain.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserDao extends PagingAndSortingRepository<User, Long>{
    User findByUsername(String username);
    User findByEmail(String email);
}
