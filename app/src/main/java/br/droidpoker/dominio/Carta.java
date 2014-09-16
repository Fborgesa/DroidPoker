package br.droidpoker.dominio;

public class Carta implements Comparable<Carta> {

	private ValorCarta valor;
	private Naipe naipe;
	private boolean visivel = false;

	public Carta(ValorCarta valor, Naipe naipe) {
        this.valor = valor;
        this.naipe = naipe;
	}

	public Naipe getNaipe() {
		return naipe;
	}

	public ValorCarta getValor() {
		return valor;
	}

    @Override
	public int compareTo(Carta outraCarta) {
		//TODO comparacao de cartas
        return 0;
	}

	public void setVisivel() {
        this.visivel = true;
	}

	public boolean isVisivel() {
		return visivel;
	}
}
