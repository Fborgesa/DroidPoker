package br.droidpoker.domain;

public enum GameState {
    GAME_START("GAME START"),
    ROUND_START("ROUND START"),
    PRE_FLOP_BETS("PRE FLOP BETS"),
    FLOP_BETS("FLOP BETS"),
    TURN_BETS("TURN BETS"),
    RIVER_BETS("RIVER BETS"),
    ROUND_FINISH("ROUND FINISH"),
    GAME_FINISH("GAME FINISH");

    private final String stateMessage;

    GameState(String state) {
        this.stateMessage = state;
    }

    @Override
    public String toString() {
        return stateMessage;
    }

    public GameState getNextState() {
        if (this.equals(GAME_FINISH)) {
            return GAME_START;
        }
        return GameState.values()[this.ordinal()+1];
    }
}
