package br.droidpoker.domain;

import android.util.Log;

import br.droidpoker.core.GameCntrllr;

public abstract class Jogador extends Entity implements Comparable<Jogador> {

    private int id;
	private String nome;
	private Mao mao;
	private int qtdFichas;
    private boolean computer;
    private boolean checked;
	private boolean folded;
    private int forcaFinal;

    /**
     * Constructor do Jogador
     * @param id
     * @param nome
     * @param fichas
     * @param isComputer
     */
	public Jogador(int id, String nome, int fichas, boolean isComputer) {
        this.id = id;
        this.nome = nome;
        this.qtdFichas = fichas;
        this.computer = isComputer;
        this.checked = false;
        this.folded = false;
	}

    public abstract void processarJogada();

    /**
     *  Metodo que informa se o jogador deu 'fold', ou seja, desistiu da rodada
     */
	public void fold() {
        this.folded = true;
	}

    /**
     * Metodo que seta que o jogador correu da rodadas
     * @return
     */
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

    /**
     * Setando mao do jogador e colocando log de saida
     * @param mao
     */
	public void setMao(Mao mao) {
        this.mao = mao;
	}

	public Mao getMao() {
		return this.mao;
	}

	public int getQtdFichas() {
		return this.qtdFichas;
	}

    /**
     * Metodo para adicionar quantia de fichas ao jogador
     * @param quantia
     */
	public void addFichas(int quantia) {
        this.qtdFichas += quantia;
	}

    /**
     * Metodo para remocao de fichas do jogador quando este aposta
     * @param quantia
     */
    public void remFichas(int quantia) {
        this.qtdFichas -= quantia;
    }

    /**
     * Metodo para verificacao se o jogador eh computador
     * @return boolean
     */
	public boolean isComputer() {
		return this.computer;
	}

    /**
     * Metodo para comparacao entre dois jogadores
     * @param outroJogador
     * @return int
     */
    @Override
	public int compareTo(Jogador outroJogador) {
		//TODO comparar jogadores
        return 0;
	}

//    @Override
//    public String toString() {
//        StringBuffer buffer = new StringBuffer();
//        buffer.append(nome);
//        buffer.append("[").append(Integer.toString(id)).append("]");
//        if (computer) {
//            buffer.append("(CPU)");
//        }
//        if (this.equals(Mesa.getInstance().getPlayerWithDealerButton())) {
//            buffer.append("(D)");
//        }
//        buffer.append(" ").append(Integer.toString(qtdFichas));
//        return buffer.toString();
//    }

    @Override
    public String toString() {
        return "Jogador" +
                "{ id=" + id +
                ", nome='" + nome + '\'' +
                ", mao=" + mao +
                ", qtdFichas=" + qtdFichas +
                ", computer=" + computer +
                ", checked=" + checked +
                ", folded=" + folded +
                '}';
    }

    /**
     * Metodo para setar que o jogador como checked na rodada
     * @param checked
     */
    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    /**
     * Metodo para verificar se o jogador jah deu fold
     * @return
     */
    public boolean isChecked() {
        return this.checked;
    }

    public void setForcaFinal (int i){
        this.forcaFinal=i;
    }

}
