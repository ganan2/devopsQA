package com.banking.dao;

import com.banking.domain.Recipient;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface RecipientDao extends PagingAndSortingRepository<Recipient, Long> {

    List<Recipient> findAll();

    Recipient findByName(String recipientName);

    void deleteByName(String recipientName);
}