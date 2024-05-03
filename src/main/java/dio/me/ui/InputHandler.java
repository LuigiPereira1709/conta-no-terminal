package main.java.dio.me.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class InputHandler {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Map<Class<?>, InputParser<?>> PARSERS = new HashMap<>();

    static {
        PARSERS.put(Integer.class, Integer::parseInt);
        PARSERS.put(Double.class, Double::parseDouble);
        PARSERS.put(String.class, s -> s);
    }

    public static <T> T readInput(Class<T> type) {
        String input = SCANNER.nextLine();
        InputParser<T> parser = getParser(type);
        if (parser == null) {
            throw new IllegalArgumentException("Unsupported input type: " + type.getSimpleName());
        }
        return parser.parse(input);
    }

    @SuppressWarnings("unchecked")
    private static <T> InputParser<T> getParser(Class<T> type) {
        return ((InputParser<T>) PARSERS.get(type));
    }

    private interface InputParser<T> {
        T parse(String input);
    }
}
