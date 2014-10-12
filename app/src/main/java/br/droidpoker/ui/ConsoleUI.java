package br.droidpoker.ui;

import br.droidpoker.dominio.Mesa;
import br.droidpoker.sistema.Model;
import br.droidpoker.sistema.View;

public class ConsoleUI extends View {

    /*
     * View must attach herself on Model and create her own Controller
     */
    public ConsoleUI() {
        super(Mesa.getInstance());
        setController(new ConsoleController(this, getModel()));
    }

    public void update() {
        //TODO metodo para atualizar interface
    }
}
