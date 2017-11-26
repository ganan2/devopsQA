package com.banking.service;

import com.banking.domain.Appointment;

import java.util.List;

/** The type appointment service */
public interface AppointmentService {

    /**
     * Create appointment
     *
     * @param appointment
     * @return
     */
    Appointment createAppointment(Appointment appointment);

    /**
     * Find all appointments
     *
     * @return
     */
    List<Appointment> findAll();

    /**
     * Find appointment
     *
     * @param id
     * @return
     */
    Appointment findAppointment(Long id);

    /**
     * Confirm appointment
     *
     * @param id
     */
    void confirmAppointment(Long id);
}
