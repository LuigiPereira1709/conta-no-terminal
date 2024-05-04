package main.java.dio.me.services;

import main.java.dio.me.domain.Account;

import java.util.Locale;

public class BalanceService {

    /**
     * Performs a withdrawal operation on the account.
     *
     * @param account    The account from which to withdraw funds.
     * @param withdrawal The amount to withdraw.
     * @return A string containing the original balance, withdrawal amount, and new balance in the format: oBalance:withdrawal:nBalance.
     * @throws IllegalArgumentException if the withdrawal amount is negative or exceeds the account balance.
     */
    public static String withdrawFromAccount(Account account, double withdrawal) {
        if (withdrawal < 0) {
            throw new IllegalArgumentException("Withdrawal amount cannot be negative.");
        }
        if (withdrawal > account.getBalance()) {
            throw new IllegalArgumentException("Insufficient funds for withdrawal.");
        }

        double originalBalance = account.getBalance();
        double newBalance = originalBalance - withdrawal;
        account.setBalance(newBalance);
        return String.format(Locale.US,"%.2f:%.2f:%.2f",
                originalBalance,
                withdrawal,
                newBalance);
    }


    /**
     * Performs a deposit operation on the account.
     *
     * @param account The account to which to deposit funds.
     * @param deposit The amount to deposit.
     * @return A string containing the original balance, deposit amount, and new balance in the format: oBalance:deposit:nBalance.
     * @throws IllegalArgumentException if the deposit amount is negative.
     */
    public static String depositToAccount(Account account, double deposit) {
        if (deposit < 0) {
            throw new IllegalArgumentException("Deposit amount cannot be negative.");
        }

        double originalBalance = account.getBalance();
        double newBalance = originalBalance + deposit;
        account.setBalance(newBalance);
        return String.format(Locale.US,"%.2f:%.2f:%.2f",
                originalBalance,
                deposit,
                newBalance);
    }
}
