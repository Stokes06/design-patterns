package fr.example.strategy;

public sealed interface PaymentStrategy permits CardPaymentStrategy, CheckPaymentStrategy {
    public abstract void pay(double amount);
}
