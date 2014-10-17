package br.droidpoker.domain;

public class Carta implements Comparable<Carta> {

	private TipoCarta nome;
	private Naipe naipe;
	private boolean visivel = false;

	public Carta(TipoCarta nome, Naipe naipe) {
        this.nome = nome;
        this.naipe = naipe;
	}

	public Naipe getNaipe() {
		return naipe;
	}

	public TipoCarta getNome() {
		return nome;
	}

    @Override
	public int compareTo(Carta outraCarta) {
		if (this.getNome().getValor() == outraCarta.getNome().getValor()) {
            return 0;
        }
        else if (this.getNome().getValor() > outraCarta.getNome().getValor()) {
            return 1;
        }
        return -1;
	}

	public void setVisivel() {
        this.visivel = true;
	}

	public boolean isVisivel() {
		return visivel;
	}

    @Override
    public String toString() {
        return getNome() + " de " + getNaipe();
    }
}
