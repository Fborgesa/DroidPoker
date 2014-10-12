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

	public void getBlinds() {
        //TODO obter blinds dos jogadores
	}

	public void distribuirCartas() {
        for (Jogador jogador: Mesa.getInstance().listJogador()) {
            Mao mao = new Mao();
            try {
                mao.addCarta(baralho.pegarDoBaralho());
                mao.addCarta(baralho.pegarDoBaralho());
                jogador.setMao(mao);
            } catch (Exception e) {
                e.printStackTrace();
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
