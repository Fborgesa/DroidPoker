package br.droidpoker.ui;

import br.droidpoker.sistema.Model;
import br.droidpoker.sistema.View;

public class ConsoleUI extends View {

    /*
     * View must attach herself on Model and create her own Controller
     */
    public ConsoleUI(Model model) {
        super(model);
        this.setController(new ConsoleController());
    }

    public void update() {
        //TODO metodo para atualizar interface
    }
}
