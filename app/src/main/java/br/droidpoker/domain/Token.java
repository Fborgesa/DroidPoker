package br.droidpoker.domain;

/**
 * Token portado por um Jogador para indicar que ele é o jogador da vez
 */
public class Token {
    private Jogador playerWithToken;

    public Token(){
    }

    public void setPlayerWithToken(Jogador playerWithToken) {
        this.playerWithToken = playerWithToken;
    }

    public Jogador getPlayerWithToken() {
        return playerWithToken;
    }

    public void passTheToken() {
        this.playerWithToken = Mesa.getInstance().getNextJogador(playerWithToken);
    }
}