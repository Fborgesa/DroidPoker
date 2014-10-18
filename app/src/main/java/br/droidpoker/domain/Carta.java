package br.droidpoker.domain;

public class Carta implements Comparable<Carta> {

	private TipoCarta nome;
	private Naipe naipe;
	private boolean visivel = false;

    /**
     * Constructor
     * @param nome
     * @param naipe
     */
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

    /**
     * Metodo de comparacao entre duas cartas
     * @param outraCarta
     * @return int
     */
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

    /**
     *  Metodo que torna a carta visivel
     */
	public void setVisivel() {
        this.visivel = true;
	}

    /**
     * Metodo para saber se a carta eh visivel
     * @return boolean
     */
	public boolean isVisivel() {
		return visivel;
	}

    @Override
    public String toString() {
        return getNome() + " de " + getNaipe();
    }
}
