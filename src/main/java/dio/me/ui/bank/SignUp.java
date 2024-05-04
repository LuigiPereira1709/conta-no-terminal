package main.java.dio.me.ui.bank;

import main.java.dio.me.domain.Account;
import main.java.dio.me.ui.InputHandler;
import main.java.dio.me.ui.commands.CreateAccount;

public class SignUp {
    private final InputHandler inputHandler;

    /**
     * Constructs a CreateAccount command with the specified InputHandler.
     *
     * @param inputHandler the input handler for user interaction
     */
    public SignUp(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
    }

    /**
     * Executes the create account command.
     *
     * @return the newly created user account
     */
    public Account execute() {
        System.out.println("=== Create Account ===");
        System.out.print("Enter your first name: ");
        String firstName = inputHandler.readInput(String.class);

        System.out.print("Enter your last name: ");
        String lastName = inputHandler.readInput(String.class);

        System.out.print("Enter your agency: ");
        String agency = inputHandler.readInput(String.class);

        return new CreateAccount(firstName, lastName, agency).execute();
    }
}

