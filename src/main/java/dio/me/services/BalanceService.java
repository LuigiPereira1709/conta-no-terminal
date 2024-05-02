package main.java.dio.me.services;

import main.java.dio.me.domain.Account;

public class BalanceService {
    public static void withdrawal(Account account, double withdrawal) {
        System.out.printf("Before withdrawal your balance is %.2f%n", account.getBalance());
        double newBalance = account.getBalance() - withdrawal;
        account.setBalance(newBalance);
        System.out.printf("After withdrawal your balance is %.2f%n", account.getBalance());
    }

    public static void deposit(Account account, double deposit){
        System.out.printf("Before deposit your balance is %.2f%n", account.getBalance());
        double newBalance = account.getBalance() + deposit;
        account.setBalance(newBalance);
        System.out.printf("After deposit your balance is %.2f%n", account.getBalance());
    }
}
