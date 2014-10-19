package br.droidpoker.core;

import java.util.ArrayList;
import java.util.List;

import br.droidpoker.domain.GameAction;
import br.droidpoker.domain.GameActionType;
import br.droidpoker.domain.GameState;
import br.droidpoker.domain.Humano;
import br.droidpoker.domain.Jogador;
import br.droidpoker.domain.Mesa;

/**
 * Controller do jogo
 */
public class GameCntrllr extends Cntrllr {

    private static GameCntrllr instance;

    public static final boolean DEBUG_MODE = true;
    public static final String DEBUG_TAG = "UnB";

    private Mesa mesa;
    private int uniqueId = 1;

    private GameCntrllr() {
        mesa = Mesa.getInstance();
    }

    /**
     * Game Controller é um singleton.
     * @return instancia do Game Controller
     */
    public static GameCntrllr getInstance() {
        if (instance == null) {
            instance = new GameCntrllr();
        }
        return instance;
    }

    /**
     * Criação de um jogo Jogo.
     * @param nomeJogadores lista do nome dos Jogadores que iniciarão a partida
     * @param blindInicial valor inicial do blind
     * @param quantiaInicial valor que cada jogador terá em fichas
     */
    public void startNewGame(String[] nomeJogadores, int blindInicial, int quantiaInicial) {
        mesa.setCurrentGameState(GameState.GAME_START);
        mesa.setBlindValue(blindInicial);
        for (String nomeJogador: nomeJogadores) {
            Jogador jgdr = new Humano(getUniqueId(), nomeJogador, quantiaInicial);
            mesa.addJogador(jgdr);
        }
        mesa.advanceToNextGameState();
    }

    /**
     * Obtem um valor único de Id. Cada jogador recebe um Id
     * @return valor do Id.
     */
    private int getUniqueId() {
        return uniqueId++;
    }

    /**
     * Notifica o game controller das mudanças no Model.
     */
    public void update() {
    }

    /**
     * Obtem a lista de possíveis ações a serem realizas no corrente estado do jogo.
     * @return possibleActions lista de ações possíveis
     */
    public List<GameActionType> listPossibleActions() {
        List<GameActionType> possibleActions = new ArrayList<GameActionType>();
        //TODO fazer com que a lista de ações dependa do estado do jogo/mesa
        possibleActions.add(GameActionType.CALL);
        possibleActions.add(GameActionType.CHECK);
        possibleActions.add(GameActionType.FOLD);
        possibleActions.add(GameActionType.RAISE);
        return possibleActions;
    }

    /**
     * Metodo para realizar a acao escolhida pelo jogador.
     * @param action ação de jogo.
     */
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
        mesa.nextTurn();
    }
}
