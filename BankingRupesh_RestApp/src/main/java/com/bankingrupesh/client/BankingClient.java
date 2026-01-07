package com.bankingrupesh.client;

import com.bankingrupesh.model.Account;
import org.springframework.web.client.RestTemplate;

public class BankingClient {
    private static final String BASE_URL = "http://localhost:8080/api/banking";
    private RestTemplate restTemplate = new RestTemplate();

    public void createAccount(String accountNumber, String accountHolderName, double initialBalance) {
        String url = BASE_URL + "/account?accountNumber=" + accountNumber +
                     "&accountHolderName=" + accountHolderName + "&initialBalance=" + initialBalance;
        Account account = restTemplate.postForObject(url, null, Account.class);
        System.out.println("Account created: " + account.getAccountNumber());
    }

    public void getAccount(String accountNumber) {
        String url = BASE_URL + "/account/" + accountNumber;
        Account account = restTemplate.getForObject(url, Account.class);
        if (account != null) {
            System.out.println("Account: " + account.getAccountNumber() + ", Holder: " + account.getAccountHolderName() + ", Balance: " + account.getBalance());
        } else {
            System.out.println("Account not found");
        }
    }

    public void deposit(String accountNumber, double amount) {
        String url = BASE_URL + "/deposit?accountNumber=" + accountNumber + "&amount=" + amount;
        String response = restTemplate.postForObject(url, null, String.class);
        System.out.println(response);
    }

    public void withdraw(String accountNumber, double amount) {
        String url = BASE_URL + "/withdraw?accountNumber=" + accountNumber + "&amount=" + amount;
        String response = restTemplate.postForObject(url, null, String.class);
        System.out.println(response);
    }

    public void getBalance(String accountNumber) {
        String url = BASE_URL + "/balance/" + accountNumber;
        Double balance = restTemplate.getForObject(url, Double.class);
        System.out.println("Balance: " + balance);
    }

    public static void main(String[] args) {
        BankingClient client = new BankingClient();

        // Example usage
        client.createAccount("12345", "John Doe", 1000.0);
        client.getAccount("12345");
        client.deposit("12345", 500.0);
        client.getBalance("12345");
        client.withdraw("12345", 200.0);
        client.getBalance("12345");
    }
}