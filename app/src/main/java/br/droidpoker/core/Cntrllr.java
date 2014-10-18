package br.droidpoker.core;

public abstract class Cntrllr implements Lstnr {
    private GameModel gameModel;
    private GameView gameView;

    public Cntrllr() {
    }

    public void init(GameModel model, GameView gameView) {
        this.setGameView(gameView);
        this.setGameModel(model);
        model.attach(this);
    }

    public GameModel getGameModel() {
        return gameModel;
    }

    public GameView getGameView() {
        return gameView;
    }

    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }
}
