package fr.example.strategy;

import java.util.Map;

public class PaymentApplication {

    ConsolePrompt consolePrompt = new ConsolePrompt();

    public static void main(String[] args) {
        new PaymentApplication().run();
    }

    public void run() {
        final double amount = askForAmount();
        final PaymentStrategy paymentStrategy = askForPaymentStrategy();
        final PaymentService paymentService = new PaymentService(paymentStrategy);
        paymentService.pay(amount);
    }

    private double askForAmount() {
        final String writeAmountMessage = "entrez un montant";
        final String amountInput = this.consolePrompt.askUntilCorrect(writeAmountMessage, s -> s.matches("^[\\d]+[.]?\\d*$"));
        return Double.parseDouble(amountInput);
    }

    private PaymentStrategy askForPaymentStrategy() {
        final String message = """
                choissisez un moyen de paiment
                1 - par carte
                2 - par cheque
                """;
        Map<Integer, Class<? extends PaymentStrategy>> paymentStrategyMap = Map.of(
                1, CardPaymentStrategy.class,
                2, CheckPaymentStrategy.class
        );
        final String choiceInput = consolePrompt.askUntilCorrect(message, s -> s.matches("^\\d$"));
        final Integer choice = Integer.parseInt(choiceInput);
        final Class<? extends PaymentStrategy> strategyClass = paymentStrategyMap.get(choice);
        if (strategyClass == null) {
            throw new RuntimeException("Mauvais choix, fin de l'opération.");
        }
        PaymentStrategy paymentStrategy = null;
        try {
            paymentStrategy = strategyClass.newInstance();
        } catch (Exception e) {
        }
        return paymentStrategy;
    }
}
