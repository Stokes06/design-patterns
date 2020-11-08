package fr.example.strategy;

import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.String.format;

public final class CardPaymentStrategy implements PaymentStrategy {

    ConsolePrompt consolePrompt = new ConsolePrompt();

    @Override
    public void pay(double amount) {
        final String code = askSecretCode();
        System.out.println("code saisie : " + code);
        System.out.printf("paiement de %s par carte%n", amount);
    }

    private String askSecretCode() {
        final String codeSecretMessage = "saisissez votre code secret à 4 chiffres";
        return consolePrompt.askUntilCorrect(codeSecretMessage, s -> s.matches("\\d{4}"));
    }
}
