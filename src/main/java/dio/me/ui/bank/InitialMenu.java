package main.java.dio.me.ui.bank;

import main.java.dio.me.domain.Account;
import main.java.dio.me.ui.InputHandler;

public class InitialMenu {
    private final InputHandler inputHandler;

    /**
     * Constructs an InitialMenu with the specified InputHandler.
     *
     * @param inputHandler the input handler for user interaction
     */
    public InitialMenu(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
    }

    public void displayMenu() {
        while (true) {
            System.out.println("=== Initial Menu ===");
            System.out.println("1. Login");
            System.out.println("2. Create Account");
            System.out.println("3. About");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int option = inputHandler.readInput(Integer.class);

            switch (option) {
                case 1:
                    System.out.println();
                    login();
                    System.out.println();
                    break;
                case 2:
                    System.out.println();
                    signUp();
                    System.out.println();
                    break;
                case 3:
                    System.out.println();
                    displayAbout();
                    System.out.println();;
                    break;
                case 4:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please choose a valid option.");
                    System.out.println();
            }
        }
    }

    private void login() {
        Account authenticatedAccount = new Login(inputHandler).execute();
        if (authenticatedAccount != null) {
            new AccountMenu(inputHandler, authenticatedAccount).displayMenu();
        }
    }

    private void signUp() {
        Account createdAccount = new SignUp(inputHandler).execute();
        if (createdAccount != null) {
            new AccountMenu(inputHandler, createdAccount).displayMenu();
        }
    }

    private void displayAbout() {
        new About().displayInfo();
    }
}
