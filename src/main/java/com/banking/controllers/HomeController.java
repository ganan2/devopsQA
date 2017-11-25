package com.banking.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Should display the welcome message
 */
@Controller
public class HomeController {

    /**
     * Should redirect to index
     * @return
     */
    @RequestMapping("/")
    public String home(){
        return "redirect:/index";
    }

    /**
     * Should display index
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        return "index";
    }
}
