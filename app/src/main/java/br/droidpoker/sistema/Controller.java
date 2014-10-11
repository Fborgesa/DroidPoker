package br.droidpoker.sistema;

public abstract class Controller extends Observer {
    private Model model;
    private View view;

    public Model getModel() {
        return model;
    }

    public View getView() {
        return view;
    }
}
