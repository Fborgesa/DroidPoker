package br.droidpoker.domain;

/**
 * Token portado por um Jogador para indicar que ele é o jogador da vez
 */
public class Token {
    private static Token instance;
    private Jogador jogadorDaVez;

    private Token(){

    }

    protected static Token getInstance() {
        if (instance == null) {
            instance = new Token();
        }
        return instance;
    }

    public void setJogadorDaVez(Jogador jogadorDaVez) {
        this.jogadorDaVez = jogadorDaVez;
    }

    public Jogador getJogadorDaVez() {
        return jogadorDaVez;
    }

}
