package br.droidpoker.domain;

/**
 * Token portado por um Jogador para indicar que ele é o jogador da vez
 * ou o dealer
 *
 */
public class Token {
    private Jogador playerWithToken;

    public Token(){
    }

    /** Setando o jogador em pose do Token
     *  @param playerWithToken jogador que pegara o Token
     */
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