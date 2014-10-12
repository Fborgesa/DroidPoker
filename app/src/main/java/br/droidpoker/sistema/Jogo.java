package br.droidpoker.sistema;

import br.droidpoker.Droidpoker;
import br.droidpoker.dominio.Humano;
import br.droidpoker.dominio.Jogador;
import br.droidpoker.dominio.Mesa;
import br.droidpoker.ui.AndroidGUI;
import br.droidpoker.ui.ConsoleUI;

public class Jogo {

    private static Jogo instance;

    public static final boolean DEBUG_MODE = true;
    public static final String DEBUG_TAG = "UnB";
    public static final UITypes UI_MODE = UITypes.ANDROID;
    public static enum UITypes {
        CONSOLE, ANDROID
    }
    public static enum States {
        EM_ANDAMENTO, AGUARDANDO_JOGADA, JOGADA_REALIZADA
    }
    public static enum PlayerActions {
        CHECK, RAISE, FOLD
    }

    Droidpoker androidActivity;

    private States currentState;
    private Mesa mesa;
    private View ui;
    private int uniqueId = 1;
    private boolean running = true;
    private boolean gameOver;

    public Jogo(Droidpoker androidActivity) {
        this.androidActivity = androidActivity;
        this.instance = this;
        // setup Model
        this.mesa = Mesa.getInstance();
        // setup View
        if (UI_MODE == UITypes.ANDROID) {
            ui = new AndroidGUI();
        }
        else if (UI_MODE == UITypes.CONSOLE) {
            ui = new ConsoleUI();
        }
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
        currentState = States.EM_ANDAMENTO;
        mesa.setLastAction("Rodada iniciada"); // Atualiza interface
        mesa.getButtonPlayer(); // força distribuição do Button
        mesa.getDealer().newBaralho(); // Cria novo baralho para rodada
        mesa.getActivePote(); // iniciar o primeiro pote
        mesa.getDealer().getBlinds(); // coleta os blinds
        mesa.getDealer().distribuirCartas(); // distribui cartas
        currentState = States.AGUARDANDO_JOGADA;
        mesa.setLastAction("Iniciando primeira rodada de apostas com o jogador " + mesa.getButtonPlayer());
        mesa.getDealer().coletarApostas();
//        while (currentState == States.AGUARDANDO_JOGADA) {
//        }
        gameOver = true;
    }



	public static Jogo getInstance() {
        return instance;
    }

    public Droidpoker getAndroidActivity() {
        return androidActivity;
    }

    public Mesa getMesa() {
        return mesa;
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

    public States getCurrentState() {
        return currentState;
    }

    public void setCurrentState(States currentState) {
        this.currentState = currentState;
    }

    public View getUi() {
        return ui;
    }
}
