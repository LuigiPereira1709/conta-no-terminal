package main.java.dio.me.ui.commands;

/**
 * Interface representing a command.
 *
 * @param <T> the type of result returned by the command execution
 */
interface Command<T> {
    T execute();
}




