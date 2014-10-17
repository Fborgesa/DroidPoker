package br.droidpoker.domain;

import java.util.HashSet;

public class Pote {

	private int total;
	private HashSet<Jogador> apostadores;
	private boolean aberto = true;

	public Pote() {
        apostadores = new HashSet<Jogador>();
	}

	public void addQuantia(int quantia) {
        this.total += quantia;
        Mesa.getInstance().setLastAction(Integer.toString(quantia) + " fichas adicionadas ao pote ("
                + Integer.toString(total) + ")");
	}

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
