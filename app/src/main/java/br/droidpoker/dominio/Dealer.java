package br.droidpoker.dominio;

public class Dealer {

	private static Dealer instance;
	private Baralho baralho;

	private Dealer() {

	}

	public static Dealer getInstance() {
		if (instance == null) {
            instance = new Dealer();
        }
        return instance;
	}

	public void newBaralho() {
        this.baralho = new Baralho();
        Mesa.getInstance().setLastAction("Novo baralho");
	}

    private Jogador getButtonPlayer() {
        for (Jogador jogador: Mesa.getInstance().listJogador()) {
            if (jogador.isTheButton()) {
                return jogador;
            }
        }
        return null;
    }

	public void getBlinds() {

        int bigBlind = Mesa.getInstance().getBlindValue();
        int smallBlind = bigBlind/2;
        Jogador jogador;
        // small blind
        jogador = Mesa.getInstance().getNextJogador(getButtonPlayer());
        jogador.remFichas(smallBlind);
        Mesa.getInstance().setLastAction("small Blind (" + smallBlind + ") "  + jogador);
        // BIG blind
        jogador = Mesa.getInstance().getNextJogador(jogador);
        jogador.remFichas(bigBlind);
        Mesa.getInstance().setLastAction("BIG Blind (" + bigBlind + ") "  + jogador);
	}

	public void distribuirCartas() {
        for (Jogador jogador: Mesa.getInstance().listJogador()) {
            if (!jogador.isGameOver()) {
                Mao mao = new Mao();
                try {
                    mao.addCarta(baralho.pegarDoBaralho());
                    mao.addCarta(baralho.pegarDoBaralho());
                    jogador.setMao(mao);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        Mesa.getInstance().setLastAction("Cartas Distribuídas");
	}

	public void coletarApostas() {
        //TODO coletar apostas dos jogadores
	}

	public void flop() {
        //TODO apresentar cartas do flop
	}

	public void turn() {
        //TODO apresentar carta do turn
	}

	public void river() {
        //TODO apresentar carta do river
	}

	public Jogador obterVencedor() {
        //TODO obter vencedor da rodada
		return null;
	}

	public void distribuirPremio() {
        //TODO distribuir pote(s) ao(s) vencedor(es)

	}
}
