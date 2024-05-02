package main.java.dio.me.services;

import main.java.dio.me.domain.Account;
import main.java.dio.me.domain.Client;

public final class UserAccountService {

    /**
     * Registers a new user with the provided information.
     *
     * @param firstName The first name of the user.
     * @param lastName  The last name of the user.
     * @param agency    The agency of the account.
     * @param balance   The initial balance of the account.
     * @return The newly created account.
     */
    public static Account registerUser(String firstName, String lastName, String agency, double balance) {
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
     */
    public static Account authenticateUser(String accountData) {
        String[] fields = accountData.split(":");

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