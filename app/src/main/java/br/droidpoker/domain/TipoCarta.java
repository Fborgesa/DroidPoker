package br.droidpoker.domain;

public enum TipoCarta {

	DOIS(2),
	TRES(3),
	QUATRO(4),
	CINCO(5),
	SEIS(6),
	SETE(7),
	OITO(8),
	NOVE(9),
	DEZ(10),
	VALETE(11),
	DAMA(12),
	REI(13),
	AS(14);

	private final int valor;

	TipoCarta(int valor) {
        this.valor = valor;
	}

	public int getValor() {
        return valor;
	}

    public TipoCarta getNext() {
        if (this.equals(TipoCarta.AS)) {
            return TipoCarta.DOIS;
        }
        else {
            return TipoCarta.values()[this.ordinal()+1];
        }
    }
}
