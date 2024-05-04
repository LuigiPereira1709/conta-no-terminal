package main.java.dio.me.ui.bank;

import main.java.dio.me.domain.Account;
import main.java.dio.me.ui.InputHandler;
import main.java.dio.me.ui.commands.AuthenticateAccount;

public class Login {
    private final InputHandler inputHandler;

    /**
     * Constructs a Login command with the specified InputHandler.
     *
     * @param inputHandler the input handler for user interaction
     */
    public Login(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
    }

    /**
     * Executes the login command.
     *
     * @return the authenticated user account
     */
    public Account execute() {
        System.out.println("=== Login ===");
        System.out.print("Enter your id: ");
        String id = inputHandler.readInput(String.class);

        return new AuthenticateAccount(id).execute();
    }
}
