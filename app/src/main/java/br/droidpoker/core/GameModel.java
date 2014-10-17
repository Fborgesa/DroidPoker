package br.droidpoker.core;

import java.util.HashSet;
import java.util.Set;

public abstract class GameModel {

    private Set<Lstnr> listeners;

    public void attach(Lstnr listener) {
        if (listeners == null) {
            this.listeners = new HashSet<Lstnr>();
        }
        listeners.add(listener);
    }

    public void dettach(Lstnr listener) {
        listeners.remove(listener);
    }

    protected void notifyListeners() {
        for (Lstnr obs: listeners) {
            obs.update();
        }
    }
}
