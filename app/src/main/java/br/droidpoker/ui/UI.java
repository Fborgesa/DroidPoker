package br.droidpoker.ui;

public abstract class UI {

	private static UI instance;
    private static UITypes uiType;
    private enum UITypes {
        CONSOLE, ANDROID
    }
	protected UI() {
	}

	public static UI getInstance() {
        if (instance == null) {
            if (uiType == UITypes.ANDROID) {
                instance = new AndroidGUI();
            }
            else if (uiType == UITypes.CONSOLE) {
                instance = new Console();
            }
        }
        return instance;
	}

	public abstract void updateModel();

}
