package main.java.dio.me.ui.bank;

import main.java.dio.me.domain.Account;
import main.java.dio.me.ui.InputHandler;
import main.java.dio.me.ui.commands.TransactionOperation;
import main.java.dio.me.ui.commands.Transactions;

public class AccountMenu {
    private final InputHandler inputHandler;
    private final Account account;

    /**
     * Constructs an AccountMenu with the specified InputHandler and account.
     *
     * @param inputHandler the input handler for user interaction
     * @param account      the authenticated or newly created account
     */
    public AccountMenu(InputHandler inputHandler, Account account) {
        this.inputHandler = inputHandler;
        this.account = account;
    }

    public void displayMenu() {
        while (true) {
            System.out.println();
            displayHeader();
            System.out.println("=== Account Menu ===");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int option = inputHandler.readInput(Integer.class);

            switch (option) {
                case 1:
                    System.out.println();
                    deposit();
                    break;
                case 2:
                    System.out.println();
                    withdraw();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please choose a valid option.");
            }
        }
    }

    private void displayHeader() {
        System.out.println("=== Account Information ===");
        System.out.println("ID: " + account.getId());
        System.out.println("Full Name: " + account.getClient().getFirstName() + " " + account.getClient().getLastName());
        System.out.println("Agency: " + account.getAgency());
        System.out.println("Balance: " + account.getBalance());
        System.out.println("Date Creation: " + account.getDateCreation());
        System.out.println();
    }

    private void deposit() {
        System.out.print("Enter deposit amount: ");
        double amount = inputHandler.readInput(Double.class);

        Transactions depositTransaction = new Transactions(account, amount, TransactionOperation.DEPOSIT);
        String result = depositTransaction.execute();
        System.out.println();
        System.out.println(result);
    }

    private void withdraw() {
        System.out.print("Enter withdrawal amount: ");
        double amount = inputHandler.readInput(Double.class);

        Transactions withdrawTransaction = new Transactions(account, amount, TransactionOperation.WITHDRAWAL);
        String result = withdrawTransaction.execute();
        System.out.println();
        System.out.println(result);
    }
}
