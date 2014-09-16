package br.droidpoker.dominio;

import java.util.ArrayList;

public class Mao implements Comparable<Mao> {

	private ArrayList<Carta> cartas;
	private int score;

    public Mao() {
    }

	public void addCarta(Carta carta) {
        this.cartas.add(carta);
	}

	public ArrayList<Carta> getCartas() {
		return cartas;
	}

	public int getScore() {
		//TODO Calcular o score da mao
		return this.score;
	}

    @Override
	public int compareTo(Mao outraMao) {
        //TODO implementar comparacao de maos
		return 0;
	}

}
