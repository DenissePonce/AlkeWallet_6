package com.alke.wallet.controller;

import com.alke.wallet.model.Transaction;
import com.alke.wallet.model.User;
import com.alke.wallet.repository.TransactionRepository;
import com.alke.wallet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TransactionController {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/transactions")
    public String showTransactions(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.loadUserByUsername(userDetails.getUsername());
        model.addAttribute("transactions", transactionRepository.findByUser(user));
        model.addAttribute("transaction", new Transaction());
        return "transactions";
    }

    @PostMapping("/transactions")
    public String addTransaction(@ModelAttribute Transaction transaction) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.loadUserByUsername(userDetails.getUsername());
        transaction.setUser(user);
        transactionRepository.save(transaction);
        return "redirect:/transactions";
    }
}
