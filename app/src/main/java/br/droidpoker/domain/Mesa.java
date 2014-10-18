package br.droidpoker.domain;

import java.util.ArrayList;

import br.droidpoker.core.GameCntrllr;
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

    private GameState currentGameState;

    private boolean gameOver;

    private Token dealerButton;
    private Token turnToken;

    /**
        Contructor
     */
    private Mesa() {
        this.dealer = Dealer.getInstance();
        this.jogadores = new ArrayList<Jogador>();
        this.dealerButton = new Token();
        this.turnToken = new Token();
        gameOver = false;
    }

    /**
     * Metodo que usa Singleton para criação da mesa. Portanto,
     * só uma mesa irá ser criada durante o jogo
     */
    public static Mesa getInstance() {
        if (instance == null) {
            instance = new Mesa();
        }
        return instance;
    }

    /**
     *   Metodo para adicionar jogador na partida
     */
    public void addJogador(Jogador jogador) {
        jogadores.add(jogador);
        this.setLastAction("Jogador " + jogador.toString() + " entrou no jogo");
    }

    /**
     *   Metodo para remocao de jogador
     */
    public void remJogador(Jogador jogador) {
        this.jogadores.remove(jogador);
    }

    /**
     *  @param jogador
     * Metodo para definicao do proximo jogador. Se o jogador for o ultimo do array
     *   de jogadores, o proximo jogador se torna o primeiro jogador do array
     */
    public Jogador getNextJogador(Jogador jogador) {
        int index = jogadores.indexOf(jogador);
        if (index == jogadores.size() - 1) {
            return jogadores.get(0); // retorno ao início da lista de jogadores
        } else {
            return jogadores.get(index + 1);
        }
    }

    /**
     * Metodo para pegar o jogador anterior. Se o jogador atual for o da posicao 0,
     * o jogador anterior eh o ultima da lista
     *
     * @param jogador
     * @return jogador
     */
    public Jogador getPreviousJogador(Jogador jogador) {
        int index = jogadores.indexOf(jogador);
        if (index == 0) {
            return jogadores.get(jogadores.size() - 1); // retorno ao início da lista de jogadores
        } else {
            return jogadores.get(index - 1);
        }
    }

    /**
     * Metodo que seta qual jogador ficara em pose do token
     * @param jogador
     */
    public void setPlayerWithDealerButton(Jogador jogador) {
        dealerButton.setPlayerWithToken(jogador);
        setLastAction(jogador + " com o Botão");
    }

    /**
     * Metodo para retornar o jogador com o token
     * @return jogador
     */
    public Jogador getPlayerWithDealerButton() {
        return dealerButton.getPlayerWithToken();
    }

    /**
     * Metodo para passar o token
     */
    public void passTheButton() {
        dealerButton.passTheToken();
    }

    /**
     * Metodo para setar o jogador da vez
     * @param jogador
     */
    public void setPlayerInTurn(Jogador jogador) {
        turnToken.setPlayerWithToken(jogador);
        setLastAction(jogador + "na vez");
    }

    /**
     * Método para retornar o jogador da vez
     * @return jogador
     */
    public Jogador getPlayerInTurn() {
        return turnToken.getPlayerWithToken();
    }

    /**
     * Metodo passar o turno do token
     */
    public void passTheTurnToken() {
        turnToken.passTheToken();
    }

    /**
     * Metodo para adicionar uma carta no array de cartas comunitarias
     * @param Carta
     */
    public void addCartaComunitaria(int Carta) {
        this.cartasComunitarias.add(this.dealer.pegarCarta());
    }

    /**
     * Metodo para adicionar novo pote na mesa
     */
    public void addNovoPote() {
        //TODO adicionar um novo pote a mesa
    }

    /**
     * Metodo para retornar o pote ativo na mesa
     * @return potes
     */
    public Pote getActivePote() {
        if (potes == null) {
            potes = new ArrayList<Pote>();
            potes.add(new Pote());
        }
        return potes.get(potes.size() - 1);
    }

    /**
     * Metodo para setar o valor do blind
     * @param valor
     */
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

    /**
     * Metodo que seta a lastAction do jogo
     * @param lastAction
     */
    public void setLastAction(String lastAction) {
        this.lastAction = (actionNumber++) + ": " + lastAction;
        this.notifyListeners();
    }

    public Dealer getDealer() {
        return dealer;
    }

    /**
     * metodo para dar uncheck em todos os players
     */
    public void uncheckAllPlayers() {
        for (Jogador jogador : jogadores) {
            jogador.setChecked(false);
        }
    }

    /**
     * Metodo de verificacao para saber se todos os jogadores já deram
     * check na rodada
     * @return boolean
     */
    public boolean isAllPlayersChecked() {
        boolean checkState = true;
        for (Jogador jogador : jogadores) {
            if (!jogador.isChecked()) {
                checkState = false;
            }
        }
        return checkState;
    }

    public GameState getCurrentGameState() {
        return currentGameState;
    }

    public void setCurrentGameState(GameState currentGameState) {
        this.currentGameState = currentGameState;
        setLastAction(currentGameState.toString());
    }

    /**
     * Setando o novo estado do jogo. Todos os jogadores dao uncheck. O jogador
     * da vez se torna o proximo jogador em relacao ao que possui o button de dealer
     * e verifica se o round acabou
     */
    public void advanceToNextGameState() {
        uncheckAllPlayers();
        setPlayerInTurn(getNextJogador(getPlayerWithDealerButton()));
        if (getCurrentGameState() == GameState.ROUND_FINISH) {
            if (gameOver) {
                setCurrentGameState(getCurrentGameState().getNextState());
            } else {
                setCurrentGameState(GameState.ROUND_START);

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

    /**
     * Metodo para verificar se o jogo acabou
     * @return boolean
     */
    public boolean isGameOver() {
        return gameOver;
    }
}