package br.droidpoker.core;

import br.droidpoker.domain.Humano;
import br.droidpoker.domain.Jogador;
import br.droidpoker.domain.Mesa;

public class GameCntrllr extends Cntrllr {

    private static GameCntrllr instance;

    public static final boolean DEBUG_MODE = true;
    public static final String DEBUG_TAG = "UnB";

    public static enum GameStates {
        AGUARDANDO_JOGADA, JOGADA_REALIZADA
    }
    private GameStates currentState;

    public static enum UITypes {
        CONSOLE, ANDROID
    }

    public static UITypes uiMode;

    private Mesa mesa;

    private boolean running = true;
    private boolean gameOver;
    private int uniqueId = 1;

    private GameCntrllr() {
        this.mesa = Mesa.getInstance();
        mesa.attach(this);
    }

    public static GameCntrllr getInstance() {
        if (instance == null) {
            if (uiMode == null) {
                uiMode = UITypes.ANDROID;
            }
            return new GameCntrllr();
        }
        return instance;
    }

    public void iniciarNovoJogo(String[] nomeJogadores, int blindInicial, int quantiaInicial) {
        mesa.setBlindValue(blindInicial);
        for (String nomeJogador: nomeJogadores) {
            Jogador jgdr = new Humano(getUniqueId(), nomeJogador, quantiaInicial);
            mesa.addJogador(jgdr);
        }
        gameLoop();
    }

    public void gameLoop() {
        while (running) {
            novaRodada();
            if (gameOver) running = false;
        }
    }

    private void novaRodada() {
        mesa.setLastAction("Rodada iniciada"); // Atualiza interface
        mesa.getButtonPlayer(); // força distribuição do Button
        mesa.getDealer().newBaralho(); // Cria novo baralho para rodada
        mesa.getActivePote(); // iniciar o primeiro pote
        mesa.getDealer().getBlinds(); // coleta os blinds
        mesa.getDealer().distribuirCartas(); // distribui cartas
        currentState = GameStates.AGUARDANDO_JOGADA;
        mesa.setLastAction("Iniciando primeira rodada de apostas com o jogador " + mesa.getButtonPlayer());
        mesa.getDealer().coletarApostas();
        gameOver = true;
    }

    public int getUniqueId() {
        return uniqueId++;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public GameStates getCurrentState() {
        return currentState;
    }

    public void setCurrentState(GameStates currentState) {
        this.currentState = currentState;
    }

    public void update() {
        if (this.getCurrentState() == GameStates.AGUARDANDO_JOGADA) {
            this.getGameView().getPlayerAction();
        }
    }

    public void doAction(Jogador.PlayerActions action) {
        switch (action) {
            case CHECK:
                this.setCurrentState(GameStates.JOGADA_REALIZADA);
                mesa.setLastAction(mesa.getButtonPlayer() + " Checked");
                mesa.passTheButton();
                break;
            case RAISE:
                this.setCurrentState(GameStates.JOGADA_REALIZADA);
                mesa.setLastAction(mesa.getButtonPlayer() + " Raise");
                mesa.passTheButton();
                break;
            case FOLD:
                this.setCurrentState(GameStates.JOGADA_REALIZADA);
                mesa.setLastAction(mesa.getButtonPlayer() + " Folded");
                mesa.passTheButton();
                break;
        }
    }

    public static void setUiMode(UITypes uiMode) {
        GameCntrllr.uiMode = uiMode;
    }

    public static UITypes getUiMode() {
        return uiMode;
    }
}
