package com.banking.dao;

import com.banking.domain.Recipient;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface RecipientDao extends PagingAndSortingRepository<Recipient, Long> {

    /**
     * Find all
     *
     * @return
     */
    List<Recipient> findAll();

    /**
     * Find by name
     *
     * @param recipientName
     * @return
     */
    Recipient findByName(String recipientName);

    /**
     * Delete by name
     *
     * @param recipientName
     */
    void deleteByName(String recipientName);
}