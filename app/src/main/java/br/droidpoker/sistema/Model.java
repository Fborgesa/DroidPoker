package br.droidpoker.sistema;

import java.util.HashSet;
import java.util.Set;

public abstract class Model {

    private Set<Observer> observers;

    public void Model() {
        this.observers = new HashSet<Observer>();
    }

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void dettach(Observer observer) {
        observers.remove(observer);
    }

    protected void notifyObservers() {
        for (Observer obs: observers) {
            obs.update();
        }
    }
}
