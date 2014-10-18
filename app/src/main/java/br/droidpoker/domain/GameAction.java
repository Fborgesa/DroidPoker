package br.droidpoker.domain;

public class GameAction {

    int number;
    Entity entity;
    GameActionType actionType;
    GameState gameState;

    public GameAction(int number, Entity entity, GameActionType actionType, GameState gameState) {
        this.gameState = gameState;
        this.entity = entity;
        this.number = number;
        this.actionType = actionType;
    }

    public int getNumber() {
        return number;
    }

    public Entity getJogador() {
        return entity;
    }

    public GameActionType getActionType() {
        return actionType;
    }

    public GameState getGameState() {
        return gameState;
    }

    @Override
    public String toString() {
        return "PlayerAction" +
                "{ number=" + number +
                ", entity=" + entity +
                ", actionType=" + actionType +
                ", gameState=" + gameState +
                '}';
    }
}
