package main.java.dio.me.ui.commands;

import main.java.dio.me.domain.Account;
import main.java.dio.me.services.persistence.PersistencePerform;

import static main.java.dio.me.services.UserAccountService.createAccount;
import static main.java.dio.me.services.persistence.PersistenceOperation.SAVE;
import static main.java.dio.me.services.persistence.FilePersistenceOperationFactory.createOperation;

public class CreateAccount implements Command<Account> {
    private final String firstName;
    private final String lastName;
    private final String agency;
    private final double balance;

    @SuppressWarnings("rawtypes")
    private final PersistencePerform save = createOperation(SAVE);

    /**
     * Constructs a CreateAccount command with the specified parameters.
     *
     * @param firstName the first name of the account holder
     * @param lastName  the last name of the account holder
     * @param agency    the agency of the account
     * @param balance   the initial balance of the account
     */
    public CreateAccount(String firstName, String lastName, String agency, double balance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.agency = agency;
        this.balance = balance;
    }

    /**
     * Executes the account creation command.
     *
     * @return the created user account
     */
    @Override
    public Account execute() {
        Account account = createAccount(firstName, lastName, agency, balance);
        save.execute(account.getAccount());
        System.out.printf("Account created successfully. Account ID: %d%n", account.getId());
        return account;
    }
}