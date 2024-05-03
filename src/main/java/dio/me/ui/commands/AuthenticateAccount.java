package main.java.dio.me.ui.commands;

import main.java.dio.me.domain.Account;

import static main.java.dio.me.services.UserAccountService.authenticateUser;

public class AuthenticateAccount implements Command<Account> {
    private final String id;

    /**
     * Constructs an AuthenticateAccount command with the specified account ID.
     *
     * @param id the ID of the account to authenticate
     */
    public AuthenticateAccount(String id) {
        this.id = id;
    }

    /**
     * Executes the authentication command.
     *
     * @return the authenticated user account
     */
    @Override
    public Account execute() {
        return authenticateUser(id);
    }
}
