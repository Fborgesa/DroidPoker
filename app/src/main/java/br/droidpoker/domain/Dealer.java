package br.droidpoker.domain;

/**
 * Implementa o conceito de Dealer
 */
public class Dealer extends Entity {

	private static Dealer instance;
	private Baralho baralho;
    private Mesa mesa;

    /**
     * Constructor
     */
	private Dealer() {
	}

    /**
     * Dealer é um Singleton.
     * @return instance instância do singleton
     */
	protected static Dealer getInstance() {
		if (instance == null) {
            instance = new Dealer();
        }
        return instance;
	}

    /**
     *  Criacao do novo baralho.
     */
    public void newBaralho() {
        mesa = Mesa.getInstance();
        this.baralho = new Baralho();
        mesa.addAction(new GameAction(mesa.getUniqueActionNumber(), this, GameActionType.NEW_DECK, mesa.getCurrentGameState()));
    }

    /**
     *  Obter uma carta do baralho.
     *  @return carta retirada do baralho
     */
    public Carta pegarCarta(){
        return this.baralho.pegarDoBaralho();
    }

    /**
     *  Recolhendo os blinds do jogo.
     */
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

    /**
     * Metodo para distribuicao de cartas para formação das mãos dos jogadores.
     */
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

    /**
     * Metodo flop , que torna visiveis as tres primeiras cartas
     * do array de cartas comunitarias
     */
	public void flop() {
        for (int i=0;i<=2;i++) {
            Carta aux = Mesa.getInstance().cartasComunitarias.get(i);
            aux.setVisivel();
        }
	}

    /**
     * Torna visivel a quarta carta do array
     */
	public void turn() {
        Carta aux = Mesa.getInstance().cartasComunitarias.get(3);
        aux.setVisivel();
	}

    /**
    * Torna visivel a quinta carta do array
    */
	public void river() {
        Carta aux = Mesa.getInstance().cartasComunitarias.get(4);
        aux.setVisivel();
	}

    /**
     * Metodo para obtencao de vencedor da rodada
     */
	public Jogador obterVencedor() {
        //TODO obter vencedor da rodada
		return null;
	}

    /**
     *   Metodo para distribuicao do pote
     */
	public void distribuirPremio() {
        //TODO distribuir pote(s) ao(s) vencedor(es)
	}

    @Override
    public String toString() {
        return "Dealer";
    }

}
