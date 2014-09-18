package br.droidpoker.dominio;


public class Mao implements Comparable<Mao> {

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

    public Mao() {
        this.cartas = new Pair();
    }

	public void addCarta(Carta carta) {
        if (this.numCartas()==0) {
            this.cartas.setFirst(carta);
        }
        else if (this.numCartas()==1) {
            this.cartas.setSecond(carta);
        }
        else {
            // exception
        }
	}

	public Pair getCartas() {
		return cartas;
	}

    public Carta getFrstCarta(){
        return this.getCartas().getFirst();
    }

    public Carta getScndCarta(){
        return this.getCartas().getSecond();
    }

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

	public int getScore() {
		//TODO Calcular o score da mao
		return this.score;
	}

    @Override
	public int compareTo(Mao outraMao) {
        //TODO implementar comparacao de maos
		return 0;
	}

}
