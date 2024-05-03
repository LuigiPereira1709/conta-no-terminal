package main.java.dio.me.services;

import main.java.dio.me.domain.Account;
import main.java.dio.me.domain.Client;
import main.java.dio.me.services.persistence.FilePersistenceOperationFactory;
import main.java.dio.me.services.persistence.PersistenceOperation;

import java.util.Map;

public final class UserAccountService {

    /**
     * Creates a new account for the user with the provided information.
     *
     * @param firstName The first name of the user.
     * @param lastName  The last name of the user.
     * @param agency    The agency of the user
     * @param balance   The initial balance of the account.
     * @return The newly created account.
     * @throws IllegalArgumentException if any of the input parameters are invalid.
     */
    public static Account createAccount(String firstName, String lastName, String agency, double balance) {
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
     * Authenticates a user using the provided account ID.
     *
     * @param id The ID of the account to authenticate.
     * @return The authenticated account.
     * @throws IllegalArgumentException if the provided account ID is invalid.
     * @throws RuntimeException         if the account with the provided ID is not found.
     */
    public static Account authenticateUser(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Invalid account data for authentication.");
        }

        Map<String, String> accountData = retrieveAccountData(id);
        String[] fullName = accountData.get("FullName").split(" ");
        String agency = accountData.get("Agency");
        String balance = accountData.get("Balance");
        String dateCreation = accountData.get("dateCreation");

        Client client = Client.ClientBuilder.aClient()
                .firstName(fullName[0])
                .lastName(fullName[1])
                .build();

        return Account.AccountBuilder.anAccount()
                .id(Long.parseLong(id))
                .agency(agency)
                .client(client)
                .balance(Double.parseDouble(balance))
                .dateCreation(dateCreation)
                .buildWithId();
    }

    /**
     * Retrieves account data associated with the provided account ID.
     *
     * @param id The ID of the account to retrieve data for.
     * @return A map containing the account data.
     * @throws RuntimeException if the account with the provided ID is not found.
     */
    private static Map<String, String> retrieveAccountData(String id) {
        Object data = FilePersistenceOperationFactory.createOperation(PersistenceOperation.LOAD).execute(null);

        Map<String, Object> mapData = (Map<String, Object>) data;

        Map<String, Object> accountsMap = (Map<String, Object>) mapData.get("Accounts");

        if (!accountsMap.containsKey(id)) {
            throw new RuntimeException("Account with " + id + " not found");
        }

        return (Map<String, String>) accountsMap.get(id);
    }
}
