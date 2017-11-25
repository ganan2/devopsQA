package com.banking.controllers;

import com.banking.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Should display the welcome message
 */
@Controller
public class HomeController {

    /**
     * Should redirect to index
     * @return
     */
    @RequestMapping(value = "/")
    public String home(){
        return "redirect:/index";
    }

    /**
     * Should display index
     * @return
     */
    @RequestMapping(value = "/index")
    public String index(){
        return "index";
    }

    /**
     * Should display signup
     * @return
     */
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signUp(Model model) {

        User user = new User();
        model.addAttribute("user", user);

        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String  signupPost(@ModelAttribute("user") User user, Model model){
        return "redirect:/";
    }
}
