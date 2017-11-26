package com.banking.controllers;

import com.banking.dao.RoleDao;
import com.banking.domain.PrimaryAccount;
import com.banking.domain.SavingsAccount;
import com.banking.domain.User;
import com.banking.domain.security.UserRole;
import com.banking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

/**
 * Should display the welcome message
 */
@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleDao roleDao;

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
    public String signupPost(
            @ModelAttribute("user") User user,
            Model model) {
        if(userService.checkUserExists(user.getUsername(), user.getEmail()))  {

            if (userService.checkEmailExists(user.getEmail())) {
                model.addAttribute("emailExists", true);
            }

            if (userService.checkUsernameExists(user.getUsername())) {
                model.addAttribute("usernameExists", true);
            }

            return "signup";
        } else {
            Set<UserRole> userRoles = new HashSet<>();
            userRoles.add(new UserRole(user, roleDao.findByName("ROLE_USER")));
            userService.createUser(user, userRoles);

            return "redirect:/";
        }
    }

    @Controller
    @RequestMapping("/account")
    public class AccountController {
        @RequestMapping("/primaryAccount")
        public String primaryAccount() {
            return "primaryAccount";
        }

        @RequestMapping("/savingsAccount")
        public String savingsAccount() {
            return "savingsAccount";
        }
    }

    @RequestMapping("/userFront")
    public String userFront(Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());
        PrimaryAccount primaryAccount = user.getPrimaryAccount();
        SavingsAccount savingsAccount = user.getSavingsAccount();

        model.addAttribute("primaryAccount", primaryAccount);
        model.addAttribute("savingsAccount", savingsAccount);

        return "userFront";
    }



}
