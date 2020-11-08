package fr.example.observer;

import javax.swing.text.DateFormatter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class CarBoard implements FuelObserver {
    private final Car car;
    private boolean showFuelAlert = false;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss:S0");

    public CarBoard(Car car) {
        this.car = car;
        this.car.registerObserver(this);
    }

    @Override
    public void update() {
        checkIfAlertShouldDisplayed();
        displayBoard();
    }

    private void checkIfAlertShouldDisplayed() {
        final double fuelPercentRemaining = this.car.getFuelPercentRemaining();
        this.showFuelAlert = fuelPercentRemaining <= 20.0;
    }
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";

    public void displayBoard() {
        System.out.print("\r");
        System.out.printf("[%s] ", LocalTime.now().format(this.dateTimeFormatter));
        if (showFuelAlert) {
            System.out.print(ANSI_RED + "⚠️ Essence ️⚠️" + ANSI_RESET);
        } else {
            System.out.print(ANSI_GREEN + "\uD83D\uDC4C" + ANSI_RESET);
        }
    }
}
