package com.banking.controllers;

import com.banking.domain.User;
import com.banking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserService userService;

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
    @RequestMapping(
            value = "/signup", method = RequestMethod.GET)
    public String signUp(Model model) {

        User user = new User();
        model.addAttribute("user", user);

        return "signup";
    }

    /**
     * Should validate username and email for signup
     * @param user
     * @param model
     * @return
     */
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String  signupPost(@ModelAttribute("user") User user, Model model){
        if(userService.checkUserExists(user.getUsername(), user.getEmail())){
            if(userService.checkUsernameExists(user.getUsername())) {
                model.addAttribute("usernameExists", true);
            }

            if(userService.checkEmailExists(user.getEmail())){
                model.addAttribute("emailExists", true);
            }
            return "signup";
        } else {
            userService.save(user);
        }

        return "redirect:/";
    }
}
