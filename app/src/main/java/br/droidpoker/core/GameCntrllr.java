package br.droidpoker.core;

import br.droidpoker.domain.Humano;
import br.droidpoker.domain.Jogador;
import br.droidpoker.domain.Mesa;

public class GameCntrllr extends Cntrllr {

    private static GameCntrllr instance;

    public static final boolean DEBUG_MODE = true;
    public static final String DEBUG_TAG = "UnB";

    private enum GameStates {
        GAME_STARTED("Jogo iniciado"),
        ROUND_STARTED("Rodada Iniciada"),
        PRE_FLOP_BETS("Apostas pré-flop"),
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

    private GameStates currentGameState;

    private Mesa mesa;
    private int uniqueId = 1;

    private GameCntrllr() {
        this.mesa = Mesa.getInstance();
        mesa.attach(this);
    }

    public static GameCntrllr getInstance() {
        if (instance == null) {
            return new GameCntrllr();
        }
        return instance;
    }

    public void startNewGame(String[] nomeJogadores, int blindInicial, int quantiaInicial) {
        mesa.setBlindValue(blindInicial);
        for (String nomeJogador: nomeJogadores) {
            Jogador jgdr = new Humano(getUniqueId(), nomeJogador, quantiaInicial);
            mesa.addJogador(jgdr);
        }
        setCurrentGameState(GameStates.GAME_STARTED);
        novaRodada();
    }

    private void novaRodada() {
        setCurrentGameState(GameStates.ROUND_STARTED);
        mesa.getButtonPlayer(); // força distribuição do Button
        mesa.getDealer().newBaralho(); // Cria novo baralho para rodada
        mesa.getActivePote(); // iniciar o primeiro pote
        mesa.getDealer().getBlinds(); // coleta os blinds
        mesa.getDealer().distribuirCartas(); // distribui cartas
        setCurrentGameState(GameStates.PRE_FLOP_BETS);
        nextTurn();
    }

    public void nextTurn() {
        // TODO Transferir coleta de apostas para o Dealer
        // mesa.getDealer().coletarApostas();
        if (mesa.isAllPlayersChecked()) {
            advanceToNextGameState();
        }
        else {
            this.getGameView().getPlayerAction();
        }
    }

    public void advanceToNextGameState() {
        mesa.uncheckAllPlayers();
        if (currentGameState == GameStates.ROUND_FINISHED) {
            novaRodada();
        }
        else {
            setCurrentGameState(currentGameState.getNextState());
            nextTurn();
        }
    }

    public int getUniqueId() {
        return uniqueId++;
    }

    public GameStates getCurrentGameState() {
        return currentGameState;
    }

    public void setCurrentGameState(GameStates currentGameState) {
        this.currentGameState = currentGameState;
        mesa.setLastAction(currentGameState.toString());
    }

    public void update() {
//        if (this.getCurrentGameState() == GameStates.PRE_FLOP_BETS) {
//            this.getGameView().getPlayerAction();
//        }
    }

    public void doAction(Jogador.PlayerActions action) {
        Jogador buttonPlayer = mesa.getButtonPlayer();
        switch (action) {
            case CHECK:
                buttonPlayer.setChecked(true);
                mesa.setLastAction(buttonPlayer + " Checked");
                break;
            case CALL:
                buttonPlayer.setChecked(true);
                mesa.setLastAction(buttonPlayer + "Called");
                break;
            case RAISE:
                mesa.uncheckAllPlayers();
                buttonPlayer.setChecked(true);
                mesa.setLastAction(buttonPlayer + " Raised");
                break;
            case FOLD:
                buttonPlayer.fold();
                mesa.setLastAction(buttonPlayer + " Folded");
                break;
        }
        mesa.passTheButton();
        nextTurn();
    }
}
