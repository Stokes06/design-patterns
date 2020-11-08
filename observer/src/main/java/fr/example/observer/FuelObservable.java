package fr.example.observer;

public interface FuelObservable {
    public void registerObserver(FuelObserver f);
    public void notifyFuel();
}
