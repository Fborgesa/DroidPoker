package br.droidpoker.core;

import android.app.Activity;

import br.droidpoker.domain.Jogador;

public abstract class GameView extends Activity implements Lstnr {
    private GameModel gameModel;
    private Cntrllr controller;

    /*
     * View must attach herself on Model
     */
    public GameView(GameModel gameModel) {
        this.gameModel = gameModel;
        gameModel.attach(this);
    }

    public GameModel getGameModel() {
        return gameModel;
    }

    public Cntrllr getController() {
        return controller;
    }

    public void setController(Cntrllr controller) {
        this.controller = controller;
    }

    public abstract void getPlayerAction();
}
