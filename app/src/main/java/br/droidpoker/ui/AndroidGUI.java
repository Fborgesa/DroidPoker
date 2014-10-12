package br.droidpoker.ui;

import br.droidpoker.Droidpoker;
import br.droidpoker.dominio.Mesa;
import br.droidpoker.sistema.Jogo;
import br.droidpoker.sistema.View;

public class AndroidGUI extends View {

    Droidpoker androidActivity;
    Jogo jogo;

    /*
     * View must attach herself on Model and create her own Controller
     */
    public AndroidGUI() {
        super(Mesa.getInstance()); // attach herself
        this.setController(new AndroidController(this, getModel())); // create Controller

        jogo = Jogo.getInstance();
        androidActivity = jogo.getAndroidActivity();
    }

    public void update() {
        androidActivity.updateGameActionsList(jogo.getMesa().getLastAction());
	}

    public Droidpoker getAndroidActivity() {
        return androidActivity;
    }
}
