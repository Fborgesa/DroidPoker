package br.droidpoker.domain;

public class Dealer {

	private static Dealer instance;
	private Baralho baralho;
    private Mesa mesa;

    /**
     * Constructor
     */
	private Dealer() {
	}

    /**
     * Arquitura Singleton, só haverá um baralho criado durante o jogo
     * @return instance
     */

	protected static Dealer getInstance() {
		if (instance == null) {
            instance = new Dealer();
        }
        return instance;
	}

    /**
     *  Metodo para criacao do novo baralho. Testa se ja ha um baralho criado
     *  na mesa, se nao houver, eh instanciando um novo dealer no metodo
     *  getInstance. Eh criado um novo baralho e setado como ultima acao um
     *  baralho novo na mesa
     */
	public void newBaralho() {
        mesa = Mesa.getInstance();
        this.baralho = new Baralho();
        mesa.setLastAction("Novo baralho na mesa");
	}

    /**
     *  Metodo para o dealer pegar a primeira carta do baralho
     */
    public Carta pegarCarta(){
        return this.baralho.pegarDoBaralho();
    }

    /**
     *   Recolhendo os blinds do jogo
     */
	public void getBlinds() {
        int bigBlind = mesa.getBlindValue();
        int smallBlind = bigBlind/2;
        Pote pote = Mesa.getInstance().getActivePote();

        // Pegando o novo jogador como um jogador apos o que possui o Button e o setando
        // como jogador do turno. Ao final , LastAction eh setada como o valor do small
        // recolhido do jogador
        Jogador jogador = mesa.getNextJogador(mesa.getPlayerWithDealerButton());
        mesa.setPlayerInTurn(jogador);
        jogador.remFichas(smallBlind);
        pote.addQuantia(smallBlind);
        pote.addApostador(jogador);
        mesa.setLastAction("Small blind (" + smallBlind + ") recolhido de "  + jogador);

        // Passando o token para o BigBlind, este agora eh setado como o jogador do turno
        // e eh depositado o balor do bigBlind no pote. Apos isso , o jogador dah check
        // para posterior verificacao de final de turno
        mesa.passTheTurnToken();
        jogador = mesa.getPlayerInTurn();
        jogador.remFichas(bigBlind);
        pote.addQuantia(bigBlind);
        pote.addApostador(jogador);
        jogador.setChecked(true);
        mesa.setLastAction("BIG blind (" + bigBlind + ") recolhido de "  + jogador);

        // Passa o token de Jogador da vez para o jogador depois do big blind
        mesa.passTheTurnToken();
	}

    /**
     * Metodo para distribuicao das cartas. Se o jogador der 'fold'
     */
	public void distribuirCartas() {
        for (Jogador jogador: Mesa.getInstance().listJogador()) {

            // Como o jogador vai dar fold sem recebecer as cartas ?? Por isso o IF tá
            // comentado !! *****
            //
            //
            // if (!jogador.isFolded()) {
                Mao mao = new Mao();
                try {
                    mao.addCarta(baralho.pegarDoBaralho());
                    mao.addCarta(baralho.pegarDoBaralho());
                    jogador.setMao(mao);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            //}
        }
        Mesa.getInstance().setLastAction("Cartas Distribuídas");
	}

    /**
     * Metodo para coleta de apostas dos jogadores
     */
	public void coletarApostas() {
        // asks player 1 if he wants to play

        //TODO coletar apostas dos jogadores
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
}
