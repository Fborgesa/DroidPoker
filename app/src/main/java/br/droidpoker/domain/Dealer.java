package br.droidpoker.domain;

public class Dealer extends Entity {

	private static Dealer instance;
	private Baralho baralho;
    private Mesa mesa;

	private Dealer() {
	}

	protected static Dealer getInstance() {
		if (instance == null) {
            instance = new Dealer();
        }
        return instance;
	}

	public void newBaralho() {
        mesa = Mesa.getInstance();
        this.baralho = new Baralho();
        mesa.addAction(new GameAction(mesa.getUniqueActionNumber(), this, GameActionType.NEW_DECK, mesa.getCurrentGameState()));
	}

    public Carta pegarCarta(){
        return this.baralho.pegarDoBaralho();
    }

	public void getBlinds() {
        int bigBlind = mesa.getBlindValue();
        int smallBlind = bigBlind/2;
        Pote pote = Mesa.getInstance().getActivePote();
        // small blind
        Jogador jogador = mesa.getNextJogador(mesa.getPlayerWithDealerButton());
        mesa.setPlayerInTurn(jogador);
        jogador.remFichas(smallBlind);
        pote.addQuantia(smallBlind);
        pote.addApostador(jogador);
        mesa.addAction(new GameAction(mesa.getUniqueActionNumber(), jogador, GameActionType.BLIND_PUT, mesa.getCurrentGameState()));
        // BIG blind
        mesa.passTheTurnToken();
        jogador = mesa.getPlayerInTurn();
        jogador.remFichas(bigBlind);
        pote.addQuantia(bigBlind);
        pote.addApostador(jogador);
        jogador.setChecked(true);
        mesa.addAction(new GameAction(mesa.getUniqueActionNumber(), jogador, GameActionType.BLIND_PUT, mesa.getCurrentGameState()));
        // Passa o token de Jogador da vez para o jogador depois do big blind
        mesa.passTheTurnToken();
	}

	public void distribuirCartas() {
        for (Jogador jogador: mesa.listJogador()) {
            if (!jogador.isFolded()) {
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
        mesa.addAction(new GameAction(mesa.getUniqueActionNumber(), this, GameActionType.HANDS, mesa.getCurrentGameState()));
	}

	public void coletarApostas() {
        // asks player 1 if he wants to play

        //TODO coletar apostas dos jogadores
	}

	public void flop() {
        for (int i=0;i<=2;i++) {
            Carta aux = Mesa.getInstance().cartasComunitarias.get(i);
            aux.setVisivel();
        }
	}

	public void turn() {
        Carta aux = Mesa.getInstance().cartasComunitarias.get(3);
        aux.setVisivel();
	}

	public void river() {
        Carta aux = Mesa.getInstance().cartasComunitarias.get(4);
        aux.setVisivel();
	}

	public Jogador obterVencedor() {
        //TODO obter vencedor da rodada
		return null;
	}

	public void distribuirPremio() {
        //TODO distribuir pote(s) ao(s) vencedor(es)

	}

    @Override
    public String toString() {
        return "Dealer";
    }
}
