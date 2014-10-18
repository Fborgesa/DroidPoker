package br.droidpoker.core;

import java.util.ArrayList;
import java.util.List;

import br.droidpoker.domain.GameAction;
import br.droidpoker.domain.GameActionType;
import br.droidpoker.domain.GameState;
import br.droidpoker.domain.Humano;
import br.droidpoker.domain.Jogador;
import br.droidpoker.domain.Mesa;

public class GameCntrllr extends Cntrllr {

    private static GameCntrllr instance;

    public static final boolean DEBUG_MODE = true;
    public static final String DEBUG_TAG = "UnB";

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
        mesa.setCurrentGameState(GameState.GAME_START);
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
        if (mesa.allPlayersChecked()) {
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

    public List<GameActionType> listPossibleActions() {
        List<GameActionType> possibleActions = new ArrayList<GameActionType>();

        //TODO fazer com que a lista de ações dependa do estado do jogo/mesa
        possibleActions.add(GameActionType.CALL);
        possibleActions.add(GameActionType.CHECK);
        possibleActions.add(GameActionType.FOLD);
        possibleActions.add(GameActionType.RAISE);
        return possibleActions;
    }

    public void doAction(GameActionType action) {
        Jogador turnPlayer = mesa.getPlayerInTurn();
        switch (action) {
            case CHECK:
                turnPlayer.setChecked(true);
                mesa.addAction(new GameAction(mesa.getUniqueActionNumber(), turnPlayer, action, mesa.getCurrentGameState()));
                break;
            case CALL:
                turnPlayer.setChecked(true);
                mesa.addAction(new GameAction(mesa.getUniqueActionNumber(), turnPlayer, action, mesa.getCurrentGameState()));
                break;
            case RAISE:
                mesa.uncheckAllPlayers();
                turnPlayer.setChecked(true);
                mesa.addAction(new GameAction(mesa.getUniqueActionNumber(), turnPlayer, action, mesa.getCurrentGameState()));
                break;
            case FOLD:
                turnPlayer.fold();
                mesa.addAction(new GameAction(mesa.getUniqueActionNumber(), turnPlayer, action, mesa.getCurrentGameState()));
                break;
        }
        mesa.passTheTurnToken();
        nextTurn();
    }


}
