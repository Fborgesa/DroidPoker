package br.droidpoker.domain;

import java.util.HashSet;

public class Pote {

	private int total;
	private HashSet<Jogador> apostadores;
	private boolean aberto = true;

    /**
     * Constructor
     */
	public Pote(){
        apostadores = new HashSet<Jogador>();
	}

    /**
     * Metodo para adicionar quantia no pote
     * @param quantia
     */
	public void addQuantia(int quantia){
        this.total += quantia;
	}

    /**
     * Metodo para adicionar jogador como apostador
     * @param jogador
     */
    public void addApostador(Jogador jogador) {
        if (!(apostadores.contains(jogador))) {
            apostadores.add(jogador);
        }
    }

    public HashSet<Jogador> getApostadores(){
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

	public void fecharPote() {
        this.aberto = false;
	}

}
