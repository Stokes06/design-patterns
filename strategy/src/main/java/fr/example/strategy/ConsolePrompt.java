package fr.example.strategy;

import java.util.Scanner;
import java.util.function.Predicate;

public class ConsolePrompt {

    private final Scanner scanner = new Scanner(System.in);

    public String askUntilCorrect(String message, Predicate<String> predicate) {
        String input = "";
        do {
            System.out.println(message);
            input = scanner.nextLine();

        } while (!predicate.test(input));
        return input;
    }
}
