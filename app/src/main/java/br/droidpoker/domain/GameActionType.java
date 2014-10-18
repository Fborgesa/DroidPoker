package br.droidpoker.domain;

public enum GameActionType {
    CHECK("CHECK"),
    CALL("CALL"),
    RAISE("RAISE"),
    FOLD("FOLD"),
    ALL_IN("ALL IN"),
    NEW_GAME_STATE("NEW GAME STATE"),
    BLIND_SET("BLIND SET"),
    BLIND_PUT("BLIND BET"),
    PLAYER_ADD("PLAYER ADD"),
    BUTTON_SET("BUTTON SET"),
    TURN_SET("TURN SET"),
    NEW_DECK("NEW DECK"),
    HANDS("HANDS DISTRIBUTED");

    private final String actionType;

    GameActionType(String actionType) {
        this.actionType = actionType;
    }

    @Override
    public String toString() {
        return actionType;
    }
}
