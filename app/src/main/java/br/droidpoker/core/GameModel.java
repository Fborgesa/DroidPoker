package br.droidpoker.core;

import java.util.HashSet;
import java.util.Set;

public abstract class GameModel {

    private Set<Lstnr> listeners;

    /**
     * Método singleton para usar 'attach' apenas uma vez na criacao
     * de listeners
     */
    public void attach(Lstnr listener) {
        if (listeners == null) {
            this.listeners = new HashSet<Lstnr>();
        }
        listeners.add(listener);
    }

    /**
     * Metodo para remover algum listener
     * @param listener
     */
    public void dettach(Lstnr listener) {
        listeners.remove(listener);
    }

    /**
     * Metodo para atualizar os listeners
     */
    protected void notifyListeners() {
        for (Lstnr obs: listeners) {
            obs.update();
        }
    }
}
