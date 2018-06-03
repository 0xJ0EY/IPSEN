package client.source.observers;

import java.util.ArrayList;

public class Observer <T> {

    private ArrayList<Observable> observers = new ArrayList<Observable>();
    private T state;

    public T getState() {
        return this.state;
    }

    public void setState(T state) {
        // Update the state
        this.state = state;

        // Update the observers
        this.notifyAllObservers();
    }

    public void attach(Observable observer) {
        this.observers.add(observer);
    }

    public void notifyAllObservers() {
        for (Observable observer : this.observers) {
            observer.updateObserver();
        }
    }
}
