package br.droidpoker.domain;

public enum GameStates {
    GAME_STARTED("Jogo iniciado"),
    ROUND_STARTED("Rodada iniciada"),
    PRE_FLOP_BETS("Apostas do Pré-flop"),
    FLOP_BETS("Apostas do Flop"),
    TURN_BETS("Apostas do Turn"),
    RIVER_BETS("Apostas do River"),
    ROUND_FINISHED("Rodada Finalizada"),
    GAME_FINISHED("Jogo Finalizado");

    private final String stateMessage;

    GameStates(String state) {
        this.stateMessage = state;
    }

    @Override
    public String toString() {
        return stateMessage;
    }

    public GameStates getNextState() {
        if (this.equals(GAME_FINISHED)) {
            return GAME_STARTED;
        }
        return GameStates.values()[this.ordinal()+1];
    }
}
