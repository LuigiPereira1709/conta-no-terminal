package main.java.dio.me.ui.commands;

import main.java.dio.me.domain.Account;
import main.java.dio.me.services.persistence.PersistencePerform;

import static main.java.dio.me.services.persistence.FilePersistenceOperationFactory.createOperation;
import static main.java.dio.me.services.persistence.PersistenceOperation.UPDATE;

public class Transactions implements Command<String> {
    private final Account account;
    private final double amount;
    private final TransactionOperation transactionOperation;

    @SuppressWarnings("rawtypes")
    private final PersistencePerform update = createOperation(UPDATE);

    /**
     * Constructs a Transactions command with the specified parameters.
     *
     * @param account              the account to perform the transaction on
     * @param amount               the amount involved in the transaction
     * @param transactionOperation the type of transaction operation to perform
     */
    public Transactions(Account account, double amount, TransactionOperation transactionOperation) {
        this.account = account;
        this.amount = amount;
        this.transactionOperation = transactionOperation;
    }

    /**
     * Executes the transaction command.
     *
     * @return a message indicating the result of the transaction
     */
    @Override
    public String execute() {
        String data = transactionOperation.execute(account, amount);

        if (data.length() != 3) {
            return data;
        }

        update.execute(account.getAccount());

        String[] dataFields = data.split(":");

        double originalBalance = Double.parseDouble(dataFields[0]);
        double transactionAmount = Double.parseDouble(dataFields[1]);
        double newBalance = Double.parseDouble(dataFields[2]);

        if (transactionOperation.equals(TransactionOperation.WITHDRAWAL)) {
            return String.format("Withdrawal successfully completed!%nPrevious Balance: $%.2f%nWithdrawal Amount: $%.2f%nNew Balance: $%.2f",
                    originalBalance, transactionAmount, newBalance);
        } else {
            return String.format("Deposit successfully completed!%nPrevious Balance: $%.2f%nDeposit Amount: $%.2f%nNew Balance: $%.2f",
                    originalBalance, transactionAmount, newBalance);
        }
    }
}