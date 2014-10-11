package br.droidpoker.sistema;

import br.droidpoker.dominio.Mesa;
import br.droidpoker.ui.AndroidGUI;
import br.droidpoker.ui.ConsoleUI;

public class Jogo {

	private static Jogo instance;
    private static UITypes uiType;
    private static View ui;
    private Mesa mesa;

    public enum UITypes {
        CONSOLE, ANDROID
    }

	private Jogo(UITypes uiType) {

        this.uiType = uiType;
        this.mesa = Mesa.getInstance();
        if (uiType == UITypes.ANDROID) {
            ui = new AndroidGUI(mesa);
        }
        else if (uiType == UITypes.CONSOLE) {
            ui = new ConsoleUI(mesa);
        }
	}

	public static Jogo getInstance() {
        if (instance == null) {
            instance = new Jogo(UITypes.ANDROID);
        }
        return instance;
    }
}
