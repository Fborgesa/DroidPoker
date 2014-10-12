package br.droidpoker.ui;

import br.droidpoker.dominio.Mesa;
import br.droidpoker.sistema.Controller;
import br.droidpoker.sistema.Jogo;
import br.droidpoker.sistema.Model;
import br.droidpoker.sistema.View;

public class AndroidController extends Controller {

    Mesa mesa;
    Jogo jogo;

    public AndroidController(View view, Model model) {
        super(view, model);
        model.attach(this);
        mesa = (Mesa) model;
        jogo = Jogo.getInstance();
    }

    @Override
    public void update() {
        if (jogo.getCurrentState() == Jogo.States.AGUARDANDO_JOGADA) {
            jogo.getAndroidActivity().getUserAction();
        }
    }
    @Override
    public void doAction(Jogo.PlayerActions action) {
        switch (action) {
            case CHECK:
                jogo.setCurrentState(Jogo.States.JOGADA_REALIZADA);
                mesa.setLastAction(mesa.getButtonPlayer() + " Checked");
                mesa.passTheButton();
                break;
            case RAISE:
                jogo.setCurrentState(Jogo.States.JOGADA_REALIZADA);
                mesa.setLastAction(mesa.getButtonPlayer() + " Raise");
                mesa.passTheButton();
                break;
            case FOLD:
                jogo.setCurrentState(Jogo.States.JOGADA_REALIZADA);
                mesa.setLastAction(mesa.getButtonPlayer() + " Folded");
                mesa.passTheButton();
                break;
        }
    }
}
