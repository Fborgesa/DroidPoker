package br.droidpoker.domain;

/**
 * Classe enum para verificao do estado do jogo. Por problemas praticos e
 * de funcionalidade, o Android possui problemas para funcionar com lacos
 * de repeticao simples. Foram criadas classes enum com essa a finalidade
 * de controle do jogo
 */
public enum GameState {
    GAME_START("GAME START"),
    ROUND_START("ROUND START"),
    PRE_FLOP_BETS("PRE-FLOP"),
    FLOP_BETS("FLOP"),
    TURN_BETS("TURN"),
    RIVER_BETS("RIVER"),
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

    /**
     * Pegando o proximo estado do jogo
     * @return GameState
     */
    public GameState getNextState() {
        if (this.equals(GAME_FINISH)) {
            return GAME_START;
        }
        return GameState.values()[this.ordinal()+1];
    }
}
