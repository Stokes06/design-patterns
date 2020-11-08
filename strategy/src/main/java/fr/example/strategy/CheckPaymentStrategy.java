package fr.example.strategy;

import java.util.Scanner;

public final class CheckPaymentStrategy implements PaymentStrategy {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void pay(double amount) {
        System.out.println("entrez votre nom");
        final String name = scanner.nextLine();
        System.out.printf("paiement de %s par cheque au nom de %s%n", amount, name);
    }
}
