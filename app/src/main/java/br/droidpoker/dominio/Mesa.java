package br.droidpoker.dominio;

import java.util.ArrayList;

public class Mesa {

	private static Mesa instance;
	private Dealer dealer;
	private ArrayList<Jogador> jogadores;
	private ArrayList<Carta> cartasComunitarias;
	private int blindValue;
	private ArrayList<Pote> potes;

    private Mesa() {
    }

	public static Mesa getInstance() {
        if (instance == null) {
            instance = new Mesa();
        }
        return instance;
	}

	public void novaRodada() {
        //TODO uma rodada do jogo
	}

	public void addJogador(Jogador jogador) {
        //TODO adicionar um jogador a mesa
	}

	public void remJogador(Jogador jogador) {
        //TODO remover um jogador da mesa
	}

	public void addCartaComunitaria(int Carta) {
        //TODO adicionar uma carta comunitaria
	}

	public ArrayList<Carta> listCartasComunitaria() {
        return cartasComunitarias;
	}

	public int getBlindValue() {
		return this.blindValue;
	}

	public void setBlindValue(int valor) {
        this.blindValue = valor;
	}

	public ArrayList<Pote> listPotes() {
		return potes;
	}

	public void addNovoPote(Pote pote) {
        //TODO adicionar um novo pote a mesa
	}
}
