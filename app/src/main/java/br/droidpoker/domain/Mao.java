package br.droidpoker.domain;


import android.util.Log;

import br.droidpoker.core.GameCntrllr;

public class Mao implements Comparable<Mao> {

    /**
     *  Dentro da classe mao, ha a classe 'Pair', que possui duas cartas
     */
    private class Pair {
        private Carta frst;
        private Carta scnd;

        Pair() {
        }

        public Carta getFirst() {
            return frst;
        }

        public void setFirst(Carta frst) {
            this.frst = frst;
        }

        public Carta getSecond() {
            return scnd;
        }

        public void setSecond(Carta scnd) {
            this.scnd = scnd;
        }
    }

    private Pair cartas;
	private int score;

    /**
     * Constructor.
     * A mao é setada como o valor da classe 'Pair', ou seja,
     * como as duas cartas lá setadas
     */
    public Mao() {
        this.cartas = new Pair();
    }

    /**
     * Adicionando cartas na mao.
     * Verifica se jah existe alguma carta nao mao, se nao existir,
     * esta se torna a primeira , caso contrario, se torna a segunda
     * @param carta
     * @throws Exception
     */
    public void addCarta(Carta carta) throws Exception {
        if (this.numCartas()==0) {
            this.cartas.setFirst(carta);
            //if (GameCntrllr.DEBUG_MODE) Log.d(GameCntrllr.DEBUG_TAG, "Carta 1: " + carta.toString());
        }
        else if (this.numCartas()==1) {
            this.cartas.setSecond(carta);
            //if (GameCntrllr.DEBUG_MODE) Log.d(GameCntrllr.DEBUG_TAG, "Carta 2: " + carta.toString());
        }
        else {
            throw new Exception();
        }
	}
    public Carta getFirst(){return this.cartas.getFirst();}
    public Carta getSecond(){return this.cartas.getSecond();}
	public Carta[] getCartas() {
		return new Carta[]{this.cartas.getFirst(), this.cartas.getSecond()};
	}

    /**
     * Metodo para contar quantas cartas existem na mao
     * @return int
     */
    public int numCartas() {
        int cnt = 0;
        if (this.cartas.getFirst() != null) {
            cnt++;
        }
        if (this.cartas.getSecond() != null) {
            cnt++;
        }
        return cnt;
    }

    /**
     * Metodo para retornar o Score das cartas
     * @return int
     */
	public int getScore() {
        score=0;
        Carta c1 = cartas.getFirst();
        Carta c2 = cartas.getSecond();
        if ((c1 != null) && (c2 != null)) {
            int scrC1 = c1.getNome().getValor();
            int scrC2 = c2.getNome().getValor();
            score=scrC1+scrC2;
            if (scrC1 == scrC2) {
                score += 10;
            }
            if (c1.getNaipe() == c2.getNaipe()) {
                score += 5;
            }
            if ((c1.getNome().equals(c2.getNome().getNext())) || (c2.getNome().equals(c1.getNome().getNext()))) {
                score += 3;
            }
        }
		return score;
	}

    @Override
	public int compareTo(Mao outraMao) {
        if (this.getScore() == outraMao.getScore()) {
            return 0;
        }
        else if (this.getScore() > outraMao.getScore()) {
            return 1;
        }
		return -1;
	}

    @Override
    public String toString() {
        return this.cartas.getFirst().toString() + " " + this.cartas.getSecond();
    }
}
