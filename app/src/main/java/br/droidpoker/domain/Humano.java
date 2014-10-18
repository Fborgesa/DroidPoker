package br.droidpoker.domain;

public class Humano extends Jogador {

    /**
     * Constructor do Humano
     * @param id
     * @param nome
     * @param fichas
     */
	public Humano(int id, String nome, int fichas) {
        super(id, nome, fichas, false);
	}

    @Override
    public void processarJogada() {

    }
}
