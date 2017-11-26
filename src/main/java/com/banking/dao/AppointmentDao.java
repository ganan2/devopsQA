package com.banking.dao;

import com.banking.domain.Appointment;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface AppointmentDao extends PagingAndSortingRepository<Appointment, Long> {

    /**
     * Find all
     *
     * @return
     */
    List<Appointment> findAll();
}