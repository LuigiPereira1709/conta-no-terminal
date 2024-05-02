package main.java.dio.me.services;

import main.java.dio.me.domain.Account;
import main.java.dio.me.domain.Client;

public final class SignUpAndInService {
    public static Account signUp(String firstName, String lastName, String agency, double balance) {
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


    // id:agency:clientName:clientLastName:balance
    public static Account signIn(String accountData) {
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