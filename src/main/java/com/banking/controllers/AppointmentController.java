package com.banking.controllers;

import com.banking.domain.Appointment;
import com.banking.domain.User;
import com.banking.service.AppointmentService;
import com.banking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/** The type Appointment controller */
@Controller
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private UserService userService;

    /**
     * This method returns appointment
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/create",method = RequestMethod.GET)
    public String createAppointment(Model model) {
        Appointment appointment = new Appointment();
        model.addAttribute("appointment", appointment);
        model.addAttribute("dateString", "");

        return "appointment";
    }

    /**
     * This method posts appointment and redirects to user front
     *
     * @param appointment
     * @param date
     * @param model
     * @param principal
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public String createAppointmentPost(
            @ModelAttribute("appointment") Appointment appointment,
            @ModelAttribute("dateString") String date,
            Model model,
            Principal principal) throws ParseException {

        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date d1 = format1.parse(date);
        appointment.setDate(d1);

        User user = userService.findByUsername(principal.getName());
        appointment.setUser(user);

        appointmentService.createAppointment(appointment);

        return "redirect:/userFront";
    }


}
