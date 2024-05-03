package main.java.dio.me.ui.commands;

import main.java.dio.me.domain.Account;

import static main.java.dio.me.services.BalanceService.depositToAccount;
import static main.java.dio.me.services.BalanceService.withdrawFromAccount;

public enum TransactionOperation {
    WITHDRAWAL {
        /**
         * Executes a withdrawal operation on the specified account.
         *
         * @param account the account to withdraw from
         * @param amount the amount to withdraw
         * @return a message indicating the result of the withdrawal
         */
        @Override
        public String execute(Account account, double amount) {
            return withdrawFromAccount(account, amount);
        }
    },
    DEPOSIT {
        /**
         * Executes a deposit operation on the specified account.
         *
         * @param account the account to deposit to
         * @param amount the amount to deposit
         * @return a message indicating the result of the deposit
         */
        @Override
        public String execute(Account account, double amount) {
            return depositToAccount(account, amount);
        }
    };

    protected abstract String execute(Account account, double amount);
}
