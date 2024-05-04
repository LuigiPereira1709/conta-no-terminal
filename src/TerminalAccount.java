import main.java.dio.me.ui.InputHandler;
import main.java.dio.me.ui.bank.InitialMenu;

import java.util.Scanner;

public class TerminalAccount {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InputHandler inputHandler = new InputHandler(scanner);

        InitialMenu initialMenu = new InitialMenu(inputHandler);
        initialMenu.displayMenu();

        scanner.close();
    }
}
