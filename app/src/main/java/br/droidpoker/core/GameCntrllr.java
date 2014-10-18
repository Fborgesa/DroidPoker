package br.droidpoker.core;

import java.util.ArrayList;
import java.util.List;

import br.droidpoker.domain.GameStates;
import br.droidpoker.domain.Humano;
import br.droidpoker.domain.Jogador;
import br.droidpoker.domain.Mesa;

public class GameCntrllr extends Cntrllr {

    private static GameCntrllr instance;

    public static final boolean DEBUG_MODE = true;
    public static final String DEBUG_TAG = "UnB";


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
        mesa = Mesa.getInstance();
    }

    public static GameCntrllr getInstance() {
        if (instance == null) {
            instance = new GameCntrllr();
        }
        return instance;
    }

    public void startNewGame(String[] nomeJogadores, int blindInicial, int quantiaInicial) {
        mesa.setBlindValue(blindInicial);
        for (String nomeJogador: nomeJogadores) {
            Jogador jgdr = new Humano(getUniqueId(), nomeJogador, quantiaInicial);
            mesa.addJogador(jgdr);
        }

        mesa.setPlayerWithDealerButton(mesa.listJogador().get(0));
        mesa.setPlayerInTurn(mesa.listJogador().get(0));

        mesa.setCurrentGameState(GameStates.GAME_STARTED);
        //TODO implementar sorteio de cartas para decidir qual jogador iniciará com o botão
        // First player to enter the game is the Dealer

        novaRodada();
    }

    public void novaRodada() {
        nextTurn();
    }

    public void nextTurn() {
        // TODO Transferir coleta de apostas para o Dealer?
        // mesa.getDealer().coletarApostas();
        if (mesa.isAllPlayersChecked()) {
           mesa.advanceToNextGameState();
        }
        else {
            this.getGameView().getPlayerAction();
        }
    }



    public int getUniqueId() {
        return uniqueId++;
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
