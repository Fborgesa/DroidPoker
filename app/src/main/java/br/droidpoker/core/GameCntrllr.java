package br.droidpoker.core;

import java.util.ArrayList;
import java.util.List;

import br.droidpoker.domain.Humano;
import br.droidpoker.domain.Jogador;
import br.droidpoker.domain.Mesa;

public class GameCntrllr extends Cntrllr {

    private static GameCntrllr instance;

    public static final boolean DEBUG_MODE = true;
    public static final String DEBUG_TAG = "UnB";

    private enum GameStates {
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

    private GameStates currentGameState;

    public static enum PlayerActions {
        CHECK("CHECK"),
        CALL("CALL"),
        RAISE("RAISE"),
        FOLD("FOLD");
        private final String actionType;
        PlayerActions(String actionType) {
            this.actionType = actionType;
        }

        @Override
        public String toString() {
            return actionType;
        }
    }

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
        // First player to enter the game is the Dealer
        mesa.setPlayerWithDealerButton(mesa.listJogador().get(0));
        novaRodada();
    }

    private void novaRodada() {
        setCurrentGameState(GameStates.ROUND_STARTED);
        mesa.getDealer().newBaralho(); // Cria novo baralho para rodada
        mesa.getDealer().getBlinds(); // coleta os blinds
        mesa.getDealer().distribuirCartas(); // distribui cartas
        setCurrentGameState(GameStates.PRE_FLOP_BETS);
        nextTurn();
    }

    public void nextTurn() {
        // TODO Transferir coleta de apostas para o Dealer?
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
        mesa.setPlayerInTurn(mesa.getNextJogador(mesa.getPlayerWithDealerButton()));
        if (currentGameState == GameStates.ROUND_FINISHED) {
            mesa.passTheButton();
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
    }

    public List<PlayerActions> getPossibleActions() {
        List<PlayerActions> possibleActions = new ArrayList<PlayerActions>();
        //TODO fazer com que a lista de ações dependa do estado do jogo/mesa
        for (PlayerActions playerAction: PlayerActions.values()) {
            possibleActions.add(playerAction);
        }
        return possibleActions;
    }

    public void doAction(PlayerActions action) {
        Jogador turnPlayer = mesa.getPlayerInTurn();
        switch (action) {
            case CHECK:
                turnPlayer.setChecked(true);
                mesa.setLastAction(turnPlayer + " Checked");
                break;
            case CALL:
                turnPlayer.setChecked(true);
                mesa.setLastAction(turnPlayer + "Called");
                break;
            case RAISE:
                mesa.uncheckAllPlayers();
                turnPlayer.setChecked(true);
                mesa.setLastAction(turnPlayer + " Raised");
                break;
            case FOLD:
                turnPlayer.fold();
                mesa.setLastAction(turnPlayer + " Folded");
                break;
        }
        mesa.passTheTurnToken();
        nextTurn();
    }
}
