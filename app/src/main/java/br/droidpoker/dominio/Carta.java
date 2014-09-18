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
		if (this.getValor().getValorCarta() == outraCarta.getValor().getValorCarta()) {
            return 0;
        }
        else if (this.getValor().getValorCarta() > outraCarta.getValor().getValorCarta()) {
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
}
