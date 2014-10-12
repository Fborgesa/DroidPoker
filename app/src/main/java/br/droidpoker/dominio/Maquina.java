package br.droidpoker.dominio;

public class Maquina extends Jogador {

	private int dificuldade;
    private enum estrategiaIA {
        ALEATORIA, AGRESSIVO, DEFENSIVO, BLEFE;
    }

	public Maquina(int id, String nome, int fichas, int dificuldade) {
        super(id, nome, fichas, true);
        this.dificuldade = dificuldade;
	}

    @Override
    public void processarJogada() {
        // TODO jogada do computador
    }


}
