package br.droidpoker.ui;

import br.droidpoker.Droidpoker;
import br.droidpoker.sistema.Jogo;
import br.droidpoker.sistema.Model;
import br.droidpoker.sistema.View;

public class AndroidGUI extends View {

    Droidpoker androidActivity;
    Jogo jogo;

    /*
     * View must attach herself on Model and create her own Controller
     */
    public AndroidGUI(Model model, Jogo jogo) {
        super(model);
        this.setController(new AndroidController());
        this.jogo = jogo;
        androidActivity = jogo.getAndroidActivity();
    }

    public void update() {
        androidActivity.updateGameActionsList(jogo.getMesa().getLastAction());
	}
}
