package br.droidpoker.domain;

import java.util.ArrayList;
import java.util.List;

import br.droidpoker.core.GameCntrllr;
import br.droidpoker.core.GameModel;

public class Mesa extends GameModel {

    private static Mesa instance;
    private ArrayList<Jogador> jogadores;
    protected ArrayList<Carta> cartasComunitarias;
    private ArrayList<Pote> potes;
    private int blindValue; // current blind value
    private Dealer dealer;

    private GameState currentGameState;
    private int uniqueActionNumber = 1;
    private List<GameAction> actions;

    private boolean gameOver;

    private Token dealerButton;
    private Token turnToken;

    private Mesa() {
        //super();
        this.dealer = Dealer.getInstance();
        this.jogadores = new ArrayList<Jogador>();
        this.dealerButton = new Token();
        this.turnToken = new Token();
        this.actions = new ArrayList<GameAction>();
        gameOver = false;
    }

    public static Mesa getInstance() {
        if (instance == null) {
            instance = new Mesa();
        }
        return instance;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void setBlindValue(int valor) {
        this.blindValue = valor;
        addAction(new GameAction(getUniqueActionNumber(), Dealer.getInstance(), GameActionType.BLIND_SET, getCurrentGameState()));
    }

    public int getBlindValue() {
        return this.blindValue;
    }

    public void addJogador(Jogador jogador) {
        jogadores.add(jogador);
        addAction(new GameAction(getUniqueActionNumber(), Dealer.getInstance(), GameActionType.PLAYER_ADD, getCurrentGameState()));
    }

    public void remJogador(Jogador jogador) {
        this.jogadores.remove(jogador);
    }

    public ArrayList<Jogador> listJogador() {
        return jogadores;
    }

    public Jogador getNextJogador(Jogador jogador) {
        int index = jogadores.indexOf(jogador);
        if (index == jogadores.size() - 1) {
            return jogadores.get(0); // retorno ao início da lista de jogadores
        } else {
            return jogadores.get(index + 1);
        }
    }

    public Jogador getPreviousJogador(Jogador jogador) {
        int index = jogadores.indexOf(jogador);
        if (index == 0) {
            return jogadores.get(jogadores.size() - 1); // retorno ao início da lista de jogadores
        } else {
            return jogadores.get(index - 1);
        }
    }

    public void setPlayerWithDealerButton(Jogador jogador) {
        dealerButton.setPlayerWithToken(jogador);
        addAction(new GameAction(getUniqueActionNumber(), Dealer.getInstance(), GameActionType.BUTTON_SET, getCurrentGameState()));
    }

    public Jogador getPlayerWithDealerButton() {
        return dealerButton.getPlayerWithToken();
    }

    public void passTheButton() {
        dealerButton.passTheToken();
    }

    public void setPlayerInTurn(Jogador jogador) {
        turnToken.setPlayerWithToken(jogador);
        addAction(new GameAction(getUniqueActionNumber(), Dealer.getInstance(), GameActionType.TURN_SET, getCurrentGameState()));
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

    public ArrayList<Carta> listCartasComunitaria() {
        return cartasComunitarias;
    }

    public void addNovoPote() {
        //TODO adicionar um novo pote a mesa
    }

    public Pote getActivePote() {
        if (potes == null) {
            potes = new ArrayList<Pote>();
            potes.add(new Pote());
        }
        return potes.get(potes.size() - 1);
    }

    public ArrayList<Pote> listPotes() {
        return potes;
    }

    public void uncheckAllPlayers() {
        for (Jogador jogador : jogadores) {
            jogador.setChecked(false);
        }
    }

    public boolean allPlayersChecked() {
        boolean checkState = true;
        for (Jogador jogador : jogadores) {
            if (!jogador.isChecked()) {
                checkState = false;
            }
        }
        return checkState;
    }

    public int getUniqueActionNumber() {
        return uniqueActionNumber++;
    }

    public void addAction(GameAction action) {
        actions.add(action);
        notifyListeners();
    }
    public String getLastAction() {
        return actions.get(actions.size()-1).toString();
    }

    public List<GameAction> getActions() {
        return actions;
    }

    public GameState getCurrentGameState() {
        return currentGameState;
    }

    public void setCurrentGameState(GameState gameState) {
        this.currentGameState = gameState;
        addAction(new GameAction(getUniqueActionNumber(), Dealer.getInstance(), GameActionType.STATE_CHANGE, gameState));
    }

    public void advanceToNextGameState() {
        uncheckAllPlayers();
        setPlayerInTurn(getNextJogador(getPlayerWithDealerButton()));
        if (getCurrentGameState() == GameState.ROUND_FINISHED) {
            if (gameOver) {
                setCurrentGameState(getCurrentGameState().getNextState());
            } else {
                setCurrentGameState(GameState.ROUND_STARTED);

//                if (getPlayerWithDealerButton() == null) { // primeira rodada do jogo não possui jogador com o botao
//
//                    setPlayerWithDealerButton(listJogador().get(0));
//                }
                //else {
                    passTheButton();
                //}
                getDealer().newBaralho(); // Cria novo baralho para rodada
                getDealer().getBlinds(); // coleta os blinds
                getDealer().distribuirCartas(); // distribui cartas
                setCurrentGameState(GameState.PRE_FLOP_BETS);
                GameCntrllr.getInstance().novaRodada();
            }
        } else {
            if (getCurrentGameState() == GameState.TURN_BETS) {
                setCurrentGameState(getCurrentGameState().getNextState());
                advanceToNextGameState();
            } else {
                setCurrentGameState(getCurrentGameState().getNextState());
                GameCntrllr.getInstance().nextTurn();
            }
        }
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public boolean isGameOver() {
        return gameOver;
    }

}