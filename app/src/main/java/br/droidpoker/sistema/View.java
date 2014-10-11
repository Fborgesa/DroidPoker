package br.droidpoker.sistema;

public abstract class View extends Observer {
    private Model model;
    private Controller controller;

    /*
     * View must attach herself on Model
     */
    public View(Model model) {
        model.attach(this);
    }

    public Model getModel() {
        return model;
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}
