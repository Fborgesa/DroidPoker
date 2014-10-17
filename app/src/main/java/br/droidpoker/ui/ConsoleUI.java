package br.droidpoker.ui;

import br.droidpoker.core.GameCntrllr;
import br.droidpoker.core.GameView;
import br.droidpoker.domain.Jogador;
import br.droidpoker.domain.Mesa;

public class ConsoleUI extends GameView {

    /*
     * View must attach herself on Model and create her own Controller
     */
    public ConsoleUI() {
        super(Mesa.getInstance());
        setController(GameCntrllr.getInstance());
    }

    public void update() {
        //TODO metodo para atualizar interface tipo console
    }

    @Override
    public void getPlayerAction() {
        // TODO metodo para obter acao do usuario no console
    }
}
