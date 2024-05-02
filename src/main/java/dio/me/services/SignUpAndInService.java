package main.java.dio.me.services;

import main.java.dio.me.domain.Account;
import main.java.dio.me.domain.Client;

public final class SignUpAndInService {
    public static Account signUp(String firstName, String lastName, String agency, double balance) {
        Client client = createClient(firstName, lastName);
        return creatAccount(client, agency, balance);
    }


    private static Client createClient(String firstName, String lastName) {
        return Client.ClientBuilder.aClient()
                .firstName(firstName)
                .lastName(lastName)
                .build();
    }

    private static Account creatAccount(Client client, String agency, double balance) {
        return Account.AccountBuilder.anAccount()
                .client(client)
                .agency(agency)
                .balance(balance)
                .build();
    }
}
