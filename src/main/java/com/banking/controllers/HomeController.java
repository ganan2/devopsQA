package com.banking.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Should display the welcome message
 */
@RestController
public class HomeController {

    @RequestMapping("/")
    public String HomeController(){
        return "Welcome to spring boot!";
    }
}
