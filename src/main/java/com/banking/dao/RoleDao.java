package com.banking.dao;

import com.banking.domain.security.Role;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RoleDao extends PagingAndSortingRepository<Role, Integer> {
    Role findByName(String name);
}