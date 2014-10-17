package br.droidpoker.domain;

import android.util.Log;

import br.droidpoker.core.GameCntrllr;

public abstract class Jogador implements Comparable<Jogador> {

    public static enum PlayerActions {
        CHECK, RAISE, FOLD
    }

	private int id;
	private String nome;
	private Mao mao;
	private int quantiaFichas;
	private int apostaAtual;
	private boolean folded = false;
	private boolean computer = false;
    private boolean gameOver = false;

	public Jogador(int id, String nome, int fichas, boolean isComputer) {
        this.id = id;
        this.nome = nome;
        this.quantiaFichas = fichas;
        this.computer = isComputer;
	}

    public abstract void processarJogada();

	public void fold() {
        this.folded = true;
	}

	public void check() {
        this.apostaAtual +=0; //daí pode checar com apostas diferentes de 0
	}

	public void raise(int quantia) {
        //TODO raise
	}

	public void allIn() {
        //TODO allin
	}

	public int getId() {
		return this.id;
	}

	public void setNome(String nome) {
        this.nome = nome;
	}

	public String getNome() {
		return this.nome;
	}

	public void setMao(Mao mao) {
        this.mao = mao;
        if (GameCntrllr.DEBUG_MODE) Log.d(GameCntrllr.DEBUG_TAG, "Mao do Jogador " + this.toString() + " " + mao.toString());
	}

	public Mao getMao() {
		return this.mao;
	}

	public int getFichas() {
		return this.quantiaFichas;
	}

	public void addFichas(int quantia) {
        this.quantiaFichas += quantia;
	}

    public void remFichas(int quantia) { this.quantiaFichas -= quantia; }

	public int getApostaAtual() {
		return this.apostaAtual;
	}

	public boolean isFold() {
		return this.folded;
	}

	public boolean isComputer() {
		return this.computer;
	}

    @Override
	public int compareTo(Jogador outroJogador) {
		//TODO comparar jogadores
        return 0;
	}

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(Integer.toString(id));
        buffer.append(": ").append(nome);
        if (computer) {
            buffer.append(" (cpu) ");
        }
        else {
            buffer.append(" (hum) ");
        }
        buffer.append(Integer.toString(quantiaFichas));
        return buffer.toString();
    }
}
