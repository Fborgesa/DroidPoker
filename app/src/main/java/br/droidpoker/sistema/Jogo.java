package br.droidpoker.sistema;

import br.droidpoker.dominio.Mesa;
import br.droidpoker.ui.UI;

public class Jogo {

	private static Jogo instance;
	private Mesa mesa;
	private UI ui;

	private Jogo() {
	}

	public static Jogo getInstance() {
        if (instance == null) {
            instance = new Jogo();
        }
        return instance;
    }

	public Mesa getMesa() {
		return this.mesa;
	}

	public UI getUI() {
		return this.ui;
	}

}
