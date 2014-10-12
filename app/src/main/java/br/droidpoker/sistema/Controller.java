package br.droidpoker.sistema;

public abstract class Controller extends Observer {
    private Model model;
    private View view;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
    }

    public Model getModel() {
        return model;
    }

    public View getView() {
        return view;
    }

    public abstract void doAction(Jogo.PlayerActions action);
}
