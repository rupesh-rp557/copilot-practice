package com.bankingrupesh.controller;

import com.bankingrupesh.model.Account;
import com.bankingrupesh.service.BankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/banking")
public class BankingController {

    @Autowired
    private BankingService bankingService;

    @PostMapping("/account")
    public ResponseEntity<Account> createAccount(@RequestParam String accountNumber,
                                                 @RequestParam String accountHolderName,
                                                 @RequestParam double initialBalance) {
        Account account = bankingService.createAccount(accountNumber, accountHolderName, initialBalance);
        return ResponseEntity.ok(account);
    }

    @GetMapping("/account/{accountNumber}")
    public ResponseEntity<Account> getAccount(@PathVariable String accountNumber) {
        Account account = bankingService.getAccount(accountNumber);
        if (account != null) {
            return ResponseEntity.ok(account);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/deposit")
    public ResponseEntity<String> deposit(@RequestParam String accountNumber, @RequestParam double amount) {
        boolean success = bankingService.deposit(accountNumber, amount);
        if (success) {
            return ResponseEntity.ok("Deposit successful");
        } else {
            return ResponseEntity.badRequest().body("Deposit failed");
        }
    }

    @PostMapping("/withdraw")
    public ResponseEntity<String> withdraw(@RequestParam String accountNumber, @RequestParam double amount) {
        boolean success = bankingService.withdraw(accountNumber, amount);
        if (success) {
            return ResponseEntity.ok("Withdrawal successful");
        } else {
            return ResponseEntity.badRequest().body("Withdrawal failed");
        }
    }

    @GetMapping("/balance/{accountNumber}")
    public ResponseEntity<Double> getBalance(@PathVariable String accountNumber) {
        double balance = bankingService.getBalance(accountNumber);
        return ResponseEntity.ok(balance);
    }
}