package br.droidpoker.sistema;

import br.droidpoker.Droidpoker;
import br.droidpoker.dominio.Humano;
import br.droidpoker.dominio.Jogador;
import br.droidpoker.dominio.Mesa;
import br.droidpoker.ui.AndroidGUI;
import br.droidpoker.ui.ConsoleUI;

public class Jogo {

    public static final boolean DEBUG_MODE = true;
    public static final String DEBUG_TAG = "UnB";
    public static final int GAME_FPS = 2;
    public static final UITypes UI_MODE = UITypes.ANDROID;
    public enum UITypes {
        CONSOLE, ANDROID
    }

    Droidpoker androidActivity;

	private static Jogo instance;
    private View ui;
    private Mesa mesa;
    private int uniqueId = 1;

    private boolean running;

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void gameLoop() {
        while (running) {

            mesa.novaRodada();

            if (mesa.isGameOver()) running = false;
        }
    }

    public void iniciarNovoJogo(String[] nomeJogadores, int blindInicial, int quantiaInicial) {
        for (String nomeJogador: nomeJogadores) {
            Jogador jgdr = new Humano(getUniqueId(), nomeJogador, quantiaInicial);
            getMesa().addJogador(jgdr);
        }
        gameLoop();
    }

    private Jogo() {
        // setup Model
        this.mesa = Mesa.getInstance();
        // setup View
        if (UI_MODE == UITypes.ANDROID) {
            ui = new AndroidGUI(mesa, this);
        }
        else if (UI_MODE == UITypes.CONSOLE) {
            ui = new ConsoleUI(mesa);
        }
	}

    private Jogo(Droidpoker androidActivity) {
        this.androidActivity = androidActivity;
        // setup Model
        this.mesa = Mesa.getInstance();
        // setup View
        if (UI_MODE == UITypes.ANDROID) {
            ui = new AndroidGUI(mesa, this);
        }
        else if (UI_MODE == UITypes.CONSOLE) {
            ui = new ConsoleUI(mesa);
        }
    }

	public static Jogo getInstance() {
        if (instance == null) {
            instance = new Jogo();
        }
        return instance;
    }

    public static Jogo getInstance(Droidpoker androidActivity) {
        if (instance == null) {
            instance = new Jogo(androidActivity);
        }
        return instance;
    }

    public Mesa getMesa() {
        return mesa;
    }
    public View getUi() {
        return ui;
    }

    public int getUniqueId() {
        return uniqueId++;
    }

    public Droidpoker getAndroidActivity() {
        return androidActivity;
    }
}
