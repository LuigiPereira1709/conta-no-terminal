package main.java.dio.me.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class InputHandler {
    private final Scanner scanner;
    private final Map<Class<?>, InputParser<?>> parsers;

    /**
     * Constructs an InputHandler with the specified Scanner.
     *
     * @param scanner the Scanner to use for input
     */
    public InputHandler(Scanner scanner) {
        this.scanner = scanner;
        this.parsers = new HashMap<>();
        initializeParsers();
    }

    private void initializeParsers() {
        parsers.put(Integer.class, Integer::parseInt);
        parsers.put(Double.class, Double::parseDouble);
        parsers.put(String.class, s -> s);
    }

    /**
     * Reads input of the specified type from the Scanner.
     *
     * @param type the Class representing the data type to read
     * @param <T>  the data type to read
     * @return the input value of the specified type
     * @throws IllegalArgumentException if the specified type is not supported
     */
    public <T> T readInput(Class<T> type) {
        String input = scanner.nextLine();
        InputParser<T> parser = getParser(type);
        if (parser == null) {
            throw new IllegalArgumentException("Unsupported input type: " + type.getSimpleName());
        }
        return parser.parse(input);
    }

    /**
     * Retrieves the parser for the specified type.
     *
     * @param type the Class representing the data type
     * @param <T>  the data type
     * @return the parser for the specified type
     */
    @SuppressWarnings("unchecked")
    private <T> InputParser<T> getParser(Class<T> type) {
        return ((InputParser<T>) parsers.get(type));
    }

    /**
     * Interface for parsing input of a specific data type.
     *
     * @param <T> the data type to parse
     */
    private interface InputParser<T> {
        /**
         * Parses the input string into the specified data type.
         *
         * @param input the input string to parse
         * @return the parsed value
         */
        T parse(String input);
    }
}
