package br.droidpoker.domain;

public class Humano extends Jogador {

	public Humano(int id, String nome, int fichas) {
        super(id, nome, fichas, false);
	}

    @Override
    public void processarJogada() {

    }
}
