package main.java.dio.me.services;

import main.java.dio.me.domain.Account;

public class BalanceService {

    /**
     * Performs a withdrawal operation on the account.
     *
     * @param account    The account from which to withdraw funds.
     * @param withdrawal The amount to withdraw.
     * @return A string containing the original balance, withdrawal amount, and new balance in the format: oBalance:withdrawal:nBalance.
     */
    public static String withdrawFromAccount(Account account, double withdrawal) {
        double originalBalance = account.getBalance();
        double newBalance = originalBalance - withdrawal;
        account.setBalance(newBalance);
        return String.format("%.2f:%.2f:%.2f",
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
     */
    public static String depositToAccount(Account account, double deposit) {
        double originalBalance = account.getBalance();
        double newBalance = originalBalance + deposit;
        account.setBalance(newBalance);
        return String.format("%.2f:%.2f:%.2f",
                originalBalance,
                deposit,
                newBalance);
    }
}
