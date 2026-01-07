package com.bankingrupesh.service;

import com.bankingrupesh.model.Account;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BankingService {
    private Map<String, Account> accounts = new HashMap<>();

    public Account createAccount(String accountNumber, String accountHolderName, double initialBalance) {
        Account account = new Account(accountNumber, accountHolderName, initialBalance);
        accounts.put(accountNumber, account);
        return account;
    }

    public Account getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public boolean deposit(String accountNumber, double amount) {
        Account account = accounts.get(accountNumber);
        if (account != null && amount > 0) {
            account.setBalance(account.getBalance() + amount);
            return true;
        }
        return false;
    }

    public boolean withdraw(String accountNumber, double amount) {
        Account account = accounts.get(accountNumber);
        if (account != null && amount > 0 && account.getBalance() >= amount) {
            account.setBalance(account.getBalance() - amount);
            return true;
        }
        return false;
    }

    public double getBalance(String accountNumber) {
        Account account = accounts.get(accountNumber);
        return account != null ? account.getBalance() : 0.0;
    }
}