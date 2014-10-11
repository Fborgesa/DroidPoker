package br.droidpoker.ui;

import br.droidpoker.sistema.Model;
import br.droidpoker.sistema.View;

public class AndroidGUI extends View {

    /*
     * View must attach herself on Model and create her own Controller
     */
    public AndroidGUI(Model model) {
        super(model);
        this.setController(new AndroidController());
    }

    public void update() {
        //TODO metodo para atualizar interface
	}

}
