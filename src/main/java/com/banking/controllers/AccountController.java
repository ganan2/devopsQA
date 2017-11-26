package com.banking.controllers;

import com.banking.domain.*;
import com.banking.service.AccountService;
import com.banking.service.TransactionService;
import com.banking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.List;

/** The type Account controller */
@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    UserService userService;

    @Autowired
    AccountService accountService;

    @Autowired
    TransactionService transactionService;

    /**
     * This method returns primary account
     *
     * @param model
     * @param principal
     * @return
     */
    @RequestMapping("/primaryAccount")
    public String primaryAccount(Model model, Principal principal){

        List<PrimaryTransaction> primaryTransactionList =
                transactionService.findPrimaryTransactionList(principal.getName());

        User user = userService.findByUsername(principal.getName());
        PrimaryAccount primaryAccount = user.getPrimaryAccount();

        model.addAttribute("primaryAccount", primaryAccount);
        model.addAttribute("primaryTransactionList", primaryTransactionList);

        return "primaryAccount";
    }

    /**
     * This method returns savings account
     *
     * @param model
     * @param principal
     * @return
     */
    @RequestMapping("/savingsAccount")
    public String savingsAccount(Model model, Principal principal){

        List<SavingsTransaction> savingsTransactionList =
                transactionService.findSavingsTransactionList(principal.getName());

        User user = userService.findByUsername(principal.getName());
        SavingsAccount savingsAccount = user.getSavingsAccount();

        model.addAttribute("primaryAccount", savingsAccount);
        model.addAttribute("savingsTransactionList", savingsTransactionList);

        return "savingsAccount";
    }

    /**
     * This method accepts deposits
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/deposit", method = RequestMethod.GET)
    public String deposit(Model model){
        model.addAttribute("accountType", "");
        model.addAttribute("amount", "");
        return "deposit";
    }

    /**
     * This method posts deposit and redirects to user front
     *
     * @param amount
     * @param accountType
     * @param principal
     * @return
     */
    @RequestMapping(value = "/deposit", method = RequestMethod.POST)
    public String depositPOST(
            @ModelAttribute("amount") String amount,
            @ModelAttribute("accountType") String accountType,
            Principal principal) {

        accountService.deposit(accountType, Double.parseDouble(amount), principal);

        return "redirect:/userFront";
    }

    /**
     * This method returns withdraw
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/withdraw", method = RequestMethod.GET)
    public String withdraw(Model model){
        model.addAttribute("accountType", "");
        model.addAttribute("amount", "");
        return "withdraw";
    }

    /**
     * This method posts withdraw and redirects to user front
     *
     * @param amount
     * @param model
     * @param principal
     * @return
     */
    @RequestMapping(value = "/withdraw", method = RequestMethod.POST)
    public String withdrawPost(
            @ModelAttribute("amount") String amount,
            @ModelAttribute("accountType") Model model,
            Principal principal) {
        model.addAttribute("accountType", "");
        model.addAttribute("amount", "");
        return "redirect:/userFront";
    }
}
