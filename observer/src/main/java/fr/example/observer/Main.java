package fr.example.observer;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class Main {
    public static void main(String[] args) {

        Car car = new Car(10);
        CarBoard carBoard = new CarBoard(car);
        try {
            car.run();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
