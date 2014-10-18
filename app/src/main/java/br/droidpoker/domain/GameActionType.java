package br.droidpoker.domain;

public enum GameActionType {
    CHECK("CHECK"),
    CALL("CALL"),
    RAISE("RAISE"),
    FOLD("FOLD"),
    STATE_CHANGE("STATE CHANGE"),
    BLIND_SET("BLIND SET"),
    PLAYER_ADD("PLAYER ADD"),
    BUTTON_SET("BUTTON SET"),
    TURN_SET("TURN SET"),
    NEW_DECK("NEW DECK"),
    BLIND_PUT("BLIND GET"),
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
