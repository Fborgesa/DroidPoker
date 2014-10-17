package br.droidpoker.domain;

import android.util.Log;

import br.droidpoker.core.GameCntrllr;

public abstract class Jogador implements Comparable<Jogador> {

    public static enum PlayerActions {
        CHECK, CALL, RAISE, FOLD
    }

	private int id;
	private String nome;
	private Mao mao;
	private int quantiaFichas;
	private int apostaAtual;
    private boolean checked;
	private boolean folded;
	private boolean computer = false;

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

    public boolean isFold() {
        return this.folded;
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

	public boolean isComputer() {
		return this.computer;
	}

    @Override
	public int compareTo(Jogador outroJogador) {
		//TODO comparar jogadores
        return 0;
	}

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(nome);
        buffer.append("[").append(Integer.toString(id)).append("]");
        if (computer) {
            buffer.append("(CPU)");
        }
        buffer.append(Integer.toString(quantiaFichas));
        return buffer.toString();
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isChecked() {
        return this.checked;
    }

}
