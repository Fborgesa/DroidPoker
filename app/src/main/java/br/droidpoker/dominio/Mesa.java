package br.droidpoker.dominio;

import java.util.ArrayList;

import br.droidpoker.sistema.Model;

public class Mesa extends Model {

	private static Mesa instance;
	private ArrayList<Jogador> jogadores;
	protected ArrayList<Carta> cartasComunitarias;
    private ArrayList<Pote> potes;
    private int blindValue; // current blind value
    private Dealer dealer;
    private String lastAction;

    private Mesa() {
        //super();
        this.dealer = Dealer.getInstance();
        this.jogadores = new ArrayList<Jogador>();
    }

	public static Mesa getInstance() {
        if (instance == null) {
            instance = new Mesa();
        }
        return instance;
	}

    // access interface for game modification by controllers

	public void addJogador(Jogador jogador) {
        jogadores.add(jogador);
        this.setLastAction("Jogador " + jogador.toString() + " entrou no jogo");
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

    public Jogador getButtonPlayer() {
        Token theButton = Token.getInstance();
        if (theButton.getJogadorDaVez() == null) {
            theButton.setJogadorDaVez(jogadores.get(0));
        }
        return theButton.getJogadorDaVez();
    }

    public void passTheButton() {
        Token.getInstance().setJogadorDaVez(getNextJogador(getButtonPlayer()));
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

	public void remJogador(Jogador jogador) {
        this.jogadores.remove(jogador);
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
        this.lastAction = lastAction;
        this.notifyObservers();
    }

    public Dealer getDealer() {
        return dealer;
    }
}
