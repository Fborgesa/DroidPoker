package br.droidpoker.dominio;

import java.util.ArrayList;

public class Pote {

	private int total;
	private ArrayList<Jogador> apostadores;
	private boolean aberto = true;

	public Pote() {
	}

	public void addQuantia(int quantia) {
        this.total += quantia;
	}

    public void addApostador(Jogador jogador) {
        apostadores.add(jogador);
    }

    public ArrayList<Jogador> getApostadores(){
        return this.apostadores;

    }
	public int getTotal() {
		return this.total;
	}

	public void setTotal(int total) {
        this.total = total;
	}

	public boolean isAberto() {
		return this.aberto;
	}

	public void fechar() {
        this.aberto = false;
	}

}
