package br.droidpoker.dominio;

public abstract class Jogador implements Comparable<Jogador> {

	private int id;
	private String nome;
	private Mao mao;
	private int quantiaFichas;
	private int apostaAtual;
	private boolean folded = false;
	private boolean computer = false;
	private boolean bigBlind;

	public Jogador(int id, String nome, int fichas, boolean isComputer) {
        this.id = id;
        this.nome = nome;
        this.quantiaFichas = fichas;
        this.computer = isComputer;
	}

	public void fold() {
        //TODO Desistencia
	}

	public void check() {
        //TODO check
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

	public void setBigBlind(boolean bigblind) {
        this.bigBlind = bigblind;
	}

	public boolean isBigBlind() {
		return this.bigBlind;
	}

}
