package br.droidpoker.domain;

import android.util.Log;

import br.droidpoker.core.GameCntrllr;

public abstract class Jogador implements Comparable<Jogador> {

    private int id;
	private String nome;
	private Mao mao;
	private int qtdFichas;
    private boolean computer;
    private boolean checked;
	private boolean folded;

	public Jogador(int id, String nome, int fichas, boolean isComputer) {
        this.id = id;
        this.nome = nome;
        this.qtdFichas = fichas;
        this.computer = isComputer;
        this.checked = false;
        this.folded = false;
	}

    public abstract void processarJogada();

	public void fold() {
        this.folded = true;
	}

    public boolean isFolded() {
        return this.folded;
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

	public int getQtdFichas() {
		return this.qtdFichas;
	}

	public void addFichas(int quantia) {
        this.qtdFichas += quantia;
	}

    public void remFichas(int quantia) { this.qtdFichas -= quantia; }

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
        if (this.equals(Mesa.getInstance().getPlayerWithDealerButton())) {
            buffer.append("(D)");
        }
        buffer.append(" ").append(Integer.toString(qtdFichas));
        return buffer.toString();
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isChecked() {
        return this.checked;
    }

}
