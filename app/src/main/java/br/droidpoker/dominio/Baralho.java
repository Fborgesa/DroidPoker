package br.droidpoker.dominio;

import java.util.Stack;

public class Baralho {

	private Stack<Carta> baralho;

	public Baralho() {
        //TODO criar baralho. Faz sentido criar um baralho que nao seja aleatorio?
	}

	private void embaralhar() {
        //TODO colocar as cartas do baralho de forma aleatoria
	}

	public Carta getCartaTopo() {
		return baralho.pop();
	}
}
