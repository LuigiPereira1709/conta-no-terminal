package main.java.dio.me.ui.commands;

public class CommandInvoker {
    /**
     * Executes the given command.
     *
     * @param command the command to execute
     */
    public void execute(Command command) {
        command.execute();
    }
}
