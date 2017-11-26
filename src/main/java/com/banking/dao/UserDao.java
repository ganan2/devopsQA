package com.banking.dao;

import com.banking.domain.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UserDao extends PagingAndSortingRepository<User, Long>{
    User findByUsername(String username);
    User findByEmail(String email);
    List<User> findAll();
}
