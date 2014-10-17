package br.droidpoker.domain;

import java.util.ArrayList;

import br.droidpoker.core.GameModel;

public class Mesa extends GameModel {

	private static Mesa instance;
	private ArrayList<Jogador> jogadores;
	protected ArrayList<Carta> cartasComunitarias;
    private ArrayList<Pote> potes;
    private int blindValue; // current blind value
    private Dealer dealer;
    private String lastAction;
    private int actionNumber = 1;

    private Token dealerButton;
    private Token turnToken;

    private Mesa() {
        //super();
        this.dealer = Dealer.getInstance();
        this.jogadores = new ArrayList<Jogador>();
        this.dealerButton = new Token();
        this.turnToken = new Token();
    }

	public static Mesa getInstance() {
        if (instance == null) {
            instance = new Mesa();
        }
        return instance;
	}

	public void addJogador(Jogador jogador) {
        jogadores.add(jogador);
        this.setLastAction("Jogador " + jogador.toString() + " entrou no jogo");
	}

    public ArrayList<Jogador> getJogadores(){
        return this.jogadores;
    }
    public void remJogador(Jogador jogador) {
        this.jogadores.remove(jogador);
    }

    public Jogador getNextJogador(Jogador jogador) {
        int index = jogadores.indexOf(jogador);
        if (index == jogadores.size()-1) {
            return jogadores.get(0); // retorno ao início da lista de jogadores
        }
        else {
            return jogadores.get(index+1);
        }
    }

    public Jogador getPreviousJogador(Jogador jogador) {
        int index = jogadores.indexOf(jogador);
        if (index == 0) {
            return jogadores.get(jogadores.size()-1); // retorno ao início da lista de jogadores
        }
        else {
            return jogadores.get(index-1);
        }
    }

    public void setPlayerWithDealerButton(Jogador jogador) {
        dealerButton.setPlayerWithToken(jogador);
        setLastAction(jogador + " com o Botão");
    }

    public Jogador getPlayerWithDealerButton() {
        return dealerButton.getPlayerWithToken();
    }

    public void passTheButton() {
        dealerButton.passTheToken();
    }

    public void setPlayerInTurn(Jogador jogador) {
        turnToken.setPlayerWithToken(jogador);
        setLastAction(jogador + "na vez");
    }

    public Jogador getPlayerInTurn() {
        return turnToken.getPlayerWithToken();
    }

    public void passTheTurnToken() {
        turnToken.passTheToken();
    }

	public void addCartaComunitaria(int Carta) {
        this.cartasComunitarias.add(this.dealer.pegarCarta());
	}

    public void addNovoPote() {
        //TODO adicionar um novo pote a mesa
    }

    public Pote getActivePote() {
        if (potes == null) {
            potes = new ArrayList<Pote>();
            potes.add(new Pote());
        }
        return potes.get(potes.size()-1);
    }

    public void setBlindValue(int valor) {
        this.blindValue = valor;
        setLastAction("Blind's value is " + Integer.toString(valor));
    }

    // functions for accessing the model data by views

    public ArrayList<Jogador> listJogador() {
        return jogadores;
    }

	public ArrayList<Carta> listCartasComunitaria() {
        return cartasComunitarias;
	}

    public ArrayList<Pote> listPotes() {
        return potes;
    }

	public int getBlindValue() {
		return this.blindValue;
	}

    public String getLastAction() {
        return lastAction;
    }

    public void setLastAction(String lastAction) {
        this.lastAction = (actionNumber++) + ": " + lastAction;
        this.notifyListeners();
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void uncheckAllPlayers() {
        for (Jogador jogador: jogadores) {
            jogador.setChecked(false);
        }
    }

    public boolean isAllPlayersChecked() {
        boolean checkState = true;
        for (Jogador jogador: jogadores) {
            if (!jogador.isChecked()) {
                checkState = false;
            }
        }
        return checkState;
    }
}
