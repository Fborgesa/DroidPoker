package br.droidpoker.dominio;

import java.util.ArrayList;

import br.droidpoker.sistema.Model;

public class Mesa extends Model {

	private static Mesa instance;
	private Dealer dealer;
	private ArrayList<Jogador> jogadores;
	private ArrayList<Carta> cartasComunitarias;
    private ArrayList<Pote> potes;
    private int blindValue; // current blind value

    private Mesa() {
        super();
        this.dealer = Dealer.getInstance();
        this.jogadores = new ArrayList<Jogador>();

    }

	public static Mesa getInstance() {
        if (instance == null) {
            instance = new Mesa();
        }
        return instance;
	}

    // access interface for game modification by controllers

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

    public void addNovoPote(Pote pote) {
        //TODO adicionar um novo pote a mesa
    }

    public void setBlindValue(int valor) {
        this.blindValue = valor;
    }

    // functions for accessing the model data by views

    public ArrayList<Jogador> listJogador() {
        return jogadores;
    }

	public ArrayList<Carta> listCartasComunitaria() {
        return cartasComunitarias;
	}

    public ArrayList<Pote> listPotes() {
        return potes;
    }

	public int getBlindValue() {
		return this.blindValue;
	}

}
