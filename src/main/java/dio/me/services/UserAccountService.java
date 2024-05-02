package main.java.dio.me.services;

import main.java.dio.me.domain.Account;
import main.java.dio.me.domain.Client;

public final class UserAccountService {

    /**
     * Creates a new account for the user with the provided information.
     *
     * @param firstName The first name of the user.
     * @param lastName The last name of the user.
     * @param agency The agency of the user
     * @param balance The initial balance of the account.
     * @return The newly created account.
     * @throws IllegalArgumentException if any of the input parameters are invalid.
     */
    public static Account createAccount(String firstName, String lastName,String agency, double balance) {
        if (firstName == null || lastName == null || agency == null || balance < 0) {
            throw new IllegalArgumentException("Invalid input parameters for creating account.");
        }

        Client client = Client.ClientBuilder.aClient()
                .firstName(firstName)
                .lastName(lastName)
                .build();

        return Account.AccountBuilder.anAccount()
                .client(client)
                .agency(agency)
                .balance(balance)
                .buildWithRandomId();
    }


    /**
     * Authenticates a user using the provided account data.
     *
     * @param accountData A string containing the account data in the format: id:agency:clientName:clientLastName:balance
     * @return The account associated with the provided data.
     * @throws IllegalArgumentException if the account data is invalid or incomplete.
     */
    public static Account authenticateUser(String accountData) {
        if (accountData == null || accountData.isEmpty()) {
            throw new IllegalArgumentException("Invalid account data for authentication.");
        }

        String[] fields = accountData.split(":");

        if (fields.length != 7) {
            throw new IllegalArgumentException("Invalid account data format for authentication.");
        }

        Client client = Client.ClientBuilder.aClient()
                .firstName(fields[2])
                .lastName(fields[3])
                .build();

        return Account.AccountBuilder.anAccount()
                .id(Long.parseLong(fields[0]))
                .agency(fields[1])
                .client(client)
                .balance(Double.parseDouble(fields[4]))
                .buildWithId();
    }
}
