package br.droidpoker.domain;

public class Maquina extends Jogador {

	private int dificuldade;

    /**
     * Estrategia de jogada do computador
     */
    private enum estrategiaIA {
        ALEATORIA, AGRESSIVO, DEFENSIVO, BLEFE;
    }

    /**
     * Constructor
     * @param id
     * @param nome
     * @param fichas
     * @param dificuldade
     */
	public Maquina(int id, String nome, int fichas, int dificuldade) {
        super(id, nome, fichas, true);
        this.dificuldade = dificuldade;
	}

    @Override
    public void processarJogada() {
        // TODO jogada do computador
    }


}
