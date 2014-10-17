package br.droidpoker.domain;

import java.util.ArrayList;
import java.util.Collections;

public class Dealer {

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
        mesa.setLastAction("Novo baralho na mesa");
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
        mesa.setLastAction("Small blind (" + smallBlind + ") recolhido de "  + jogador);
        // BIG blind
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

	public void distribuirCartas() {
        for (Jogador jogador: Mesa.getInstance().listJogador()) {
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
        Mesa.getInstance().setLastAction("Cartas Distribuídas");
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
        ArrayList<Jogador> remanescentes;
        remanescentes = new ArrayList<Jogador>();
        ArrayList<Jogador> todos = Mesa.getInstance().getJogadores();
        for (int i=0; i<= todos.size();i++){
            if(todos.get(i).isFolded()==false) remanescentes.add(todos.get(i));
        }

        for(Jogador atual : remanescentes) {
            ArrayList<Carta> jogada = new ArrayList<Carta>();
            jogada.add(atual.getMao().getFirst());
            jogada.add(atual.getMao().getSecond());
            for (int j=0; j<= Mesa.getInstance().cartasComunitarias.size();j++) {
                jogada.add(Mesa.getInstance().cartasComunitarias.get(j));
            }

            Collections.sort(jogada);


        }

		return null;
	}

	public void distribuirPremio() {
        //TODO distribuir pote(s) ao(s) vencedor(es)

	}
}
