package fr.example.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorCompletionService;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class Car implements FuelObservable{
    private boolean isRunning = false;
    private int remainingFuel;
    private final int maxFuelContains;
    private final List<FuelObserver> observers = new ArrayList<>();
    public Car() {
        this(30);
    }
    public Car(int maxFuelContains) {
        this.maxFuelContains = maxFuelContains;
        this.remainingFuel = this.maxFuelContains;
    }

    public void run() throws Exception{
        System.out.println("the \uD83C\uDFCE️ is starting");
        isRunning = true;
        while (isRunning) {
            Thread.sleep(100);
            this.useFuel();
            if (this.getFuelPercentRemaining() <= 10.0) {
                setRemainingFuel(this.maxFuelContains);
            }
        }
    }

    public void setRemainingFuel(int remainingFuel) {
        this.remainingFuel = remainingFuel;
        notifyFuel();
    }

    private void useFuel() {
        setRemainingFuel(this.remainingFuel - 1);
        if(remainingFuel <= 0){
            isRunning = false;
        }
    }

    @Override
    public void registerObserver(FuelObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyFuel() {
        for(int i = 0; i< observers.size(); i++){
            observers.get(i).update();
        }
    }

    public double getFuelPercentRemaining() {
        return ((double)remainingFuel / maxFuelContains) * 100.0 ;
    }
}
