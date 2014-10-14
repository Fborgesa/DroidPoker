package br.droidpoker.dominio;

public class Dealer {

	private static Dealer instance;
	private Baralho baralho;

	private Dealer() {
	}

	protected static Dealer getInstance() {
		if (instance == null) {
            instance = new Dealer();
        }
        return instance;
	}

	public void newBaralho() {
        this.baralho = new Baralho();
        Mesa.getInstance().setLastAction("Novo baralho");
	}

    public Carta pegarCarta(){
        return this.baralho.pegarDoBaralho();
    }

	public void getBlinds() {
        int bigBlind = Mesa.getInstance().getBlindValue();
        int smallBlind = bigBlind/2;
        Jogador jogador;
        Pote pote = Mesa.getInstance().getActivePote();
        // small blind
        jogador = Mesa.getInstance().getNextJogador(Mesa.getInstance().getButtonPlayer());
        jogador.remFichas(smallBlind);
        pote.addQuantia(smallBlind);
        pote.addApostador(jogador);
        Mesa.getInstance().setLastAction("small Blind (" + smallBlind + ") "  + jogador);
        // BIG blind
        jogador = Mesa.getInstance().getNextJogador(jogador);
        jogador.remFichas(bigBlind);
        pote.addQuantia(bigBlind);
        pote.addApostador(jogador);
        Mesa.getInstance().setLastAction("BIG Blind (" + bigBlind + ") "  + jogador);
        // Passa o token de Jogador da vez para o jogador depois do big blind
        Token.getInstance().setJogadorDaVez(Mesa.getInstance().getNextJogador(jogador));
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
        // asks player 1 if he wants to play

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
