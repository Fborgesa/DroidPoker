package br.droidpoker.domain;

import java.util.ArrayList;
import java.util.List;

import br.droidpoker.core.GameCntrllr;
import br.droidpoker.core.GameModel;

/**
 * Fachada do componente Model do DroidPoker
 * Implementa o conceito de Mesa do jogo
 */
public class Mesa extends GameModel {

    private static Mesa instance;
    private ArrayList<Jogador> jogadores;
    protected ArrayList<Carta> cartasComunitarias;
    private ArrayList<Pote> potes;
    private int blindValue;
    private Dealer dealer;
    private GameState currentGameState;
    private int uniqueActionNumber = 1;
    private List<GameAction> actions;
    private boolean gameOver;
    private Token dealerButton;
    private Token turnToken;

    private Mesa() {
        this.dealer = Dealer.getInstance();
        this.jogadores = new ArrayList<Jogador>();
        this.dealerButton = new Token();
        this.turnToken = new Token();
        this.actions = new ArrayList<GameAction>();
        gameOver = false;
    }

    /**
     * Mesa é um singleton.
     * @return instancia da mesa
     */
    public static Mesa getInstance() {
        if (instance == null) {
            instance = new Mesa();
        }
        return instance;
    }

    /**
     * Obtem o dealer da mesa.
     * @return um objeto tipo Dealer
     */
    public Dealer getDealer() {
        return dealer;
    }

    /**
     * Adiciona um jogador na partida.
     * @param jogador jogador a ser adicionado
     */
    public void addJogador(Jogador jogador) {
        jogadores.add(jogador);
        addAction(new GameAction(getUniqueActionNumber(), Dealer.getInstance(), GameActionType.PLAYER_ADD, getCurrentGameState()));
    }

    /**
     * Remove um jogador da partida.
     * @param jogador jogador a ser removido
     */
    public void remJogador(Jogador jogador) {
        this.jogadores.remove(jogador);
    }

    /**
     * Obter lista de jogadores da mesa.
     * @return lista de jogadores
     */
    public ArrayList<Jogador> listJogador() {
        return jogadores;
    }

    /**
     * Obtem o próximo jogador.
     *  @param jogador jogador que servira de referência para obter o próximo
     *  @return Jogador que é o próximo em relação a um jogador informado como parâmetro
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
     * Obtem o jogador anterior.
     * @param jogador jogador que servira de referência para obter o jogador anterior
     * @return jogador Jogador que é o anterior em relação a um jogador informado como parâmetro
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
     * Definir o jogador que receberá o botão (Dealer Button).
     * @param jogador jogador que receberá o botão
     */
    public void setPlayerWithDealerButton(Jogador jogador) {
        dealerButton.setPlayerWithToken(jogador);
        addAction(new GameAction(getUniqueActionNumber(), Dealer.getInstance(), GameActionType.BUTTON_SET, getCurrentGameState()));
    }

    /**
     * Obter o jogador que está de posse do botão (Dealer Button).
     * @return jogador que está com o botão
     */
    public Jogador getPlayerWithDealerButton() {
        return dealerButton.getPlayerWithToken();
    }

    /**
     * Passa o botão (Deler Button) para o próximo jogador da mesa.
     */
    public void passTheButton() {
        dealerButton.passTheToken();
    }

    /**
     * Define o jogador da vez.
     * @param jogador jogador que deverá jogar
     */
    public void setPlayerInTurn(Jogador jogador) {
        turnToken.setPlayerWithToken(jogador);
        addAction(new GameAction(getUniqueActionNumber(), Dealer.getInstance(), GameActionType.TURN_SET, getCurrentGameState()));
    }

    /**
     * Obter jogador da vez.
     * @return jogador da vez
     */
    public Jogador getPlayerInTurn() {
        return turnToken.getPlayerWithToken();
    }

    /**
     * Passa a vez do jogo para o próximo jogador da mesa.
     */
    public void passTheTurnToken() {
        turnToken.passTheToken();
    }

    /**
     * Adiciona um novo pote na mesa. O pote onde serão depositadas as novas apostas.
     */
    protected void addNovoPote() {
        potes.add(new Pote());
        // TODO tratar split de potes
    }

    /**
     * Retorna o pote ativo na mesa.
     * @return pote onde estão depositadas as apostas dos jogadores.
     */
    public Pote getActivePote() {
        if (potes == null) {
            potes = new ArrayList<Pote>();
            potes.add(new Pote());
        }
        return potes.get(potes.size() - 1);
    }

    /**
     * Obter potes da mesa.
     * @return lista de potes com apostas dos jogadores
     */
    protected ArrayList<Pote> listPotes() {
        return potes;
    }

    /**
     * Resetar a lista de potes da mesa para iniciar uma nova rodada.
     */
    protected void resetPotes() {
        potes = new ArrayList<Pote>();
    }

    /**
     * Metodo para setar o valor do blind do jogo/rodada.
     * @param valor do blind
     */
    public void setBlindValue(int valor) {
        this.blindValue = valor;
        addAction(new GameAction(getUniqueActionNumber(), Dealer.getInstance(), GameActionType.BLIND_SET, getCurrentGameState()));
    }

    /**
     * Obtem o valor atual do blind.
     * @return valor do blind
     */
    public int getBlindValue() {
        return this.blindValue;
    }

    /**
     * Adiciona uma carta na lista de cartas comunitárias.
     * @param carta a ser adicionada
     */
    public void addCartaComunitaria(Carta carta) {
        cartasComunitarias.add(carta);
    }

    /**
     * Obter cartas comunitárias adicionadas a mesa.
     * @return lista de cartas comunitárias da mesa
     */
    public ArrayList<Carta> listCartasComunitaria() {
        return cartasComunitarias;
    }

    /**
     * Obtem a ultima ação realizada no jogo.
     * @return ação do jogo
     */
    public GameAction getLastAction() {
        return actions.get(actions.size()-1);
    }

    /**
     * Adiciona uma ação à lista de ações ao jogo. Os listeners serão notificados.
     * @param action
     */
    public void addAction(GameAction action) {
        actions.add(action);
        notifyListeners();
    }

    /**
     * Lista ações realizadas no jogo.
     * @return lista de ações
     */
    public List<GameAction> getActions() {
        return actions;
    }

    /**
     * Realiza "uncheck" em todos os jogadores da mesa.
     */
    public void uncheckAllPlayers() {
        for (Jogador jogador : jogadores) {
            jogador.setChecked(false);
        }
    }

    /**
     * Verifica se todos os jogadores estão "checked" na rodada.
     * @return boolean
     */
    public boolean areAllPlayersChecked() {
        boolean checkState = true;
        for (Jogador jogador : jogadores) {
            if (!jogador.isChecked()) {
                checkState = false;
            }
        }
        return checkState;
    }

    /**
     * Obtem o atual estado do jogo.
     * @return estado de jogo
     */
    public GameState getCurrentGameState() {
        return currentGameState;
    }

    /**
     * Define o atual estado do jogo.
     * @param gameState o novo estado de jogo
     */
    public void setCurrentGameState(GameState gameState) {
        this.currentGameState = gameState;
        addAction(new GameAction(getUniqueActionNumber(), Dealer.getInstance(), GameActionType.NEW_GAME_STATE, gameState));
    }

    /**
     * Avança o estado do jogo para o próximo estado.
     */
    public void advanceToNextGameState() {
        switch(currentGameState) {
            case GAME_START:
                //TODO implementar sorteio de cartas para decidir qual jogador iniciará com o botão
                setPlayerWithDealerButton(jogadores.get(0));
                setPlayerInTurn(jogadores.get(0));
                setCurrentGameState(GameState.ROUND_START);
                advanceToNextGameState();
                break;
            case ROUND_START:
                dealer.newBaralho();
                dealer.getBlinds();
                dealer.distribuirCartas();
                setCurrentGameState(GameState.PRE_FLOP_BETS);
                uncheckAllPlayers();
                advanceToNextGameState();
                break;
            case PRE_FLOP_BETS:
                nextTurn();
                break;
            case FLOP_BETS:
                nextTurn();
                break;
            case TURN_BETS:
                nextTurn();
                break;
            case RIVER_BETS:
                nextTurn();
                break;
            case ROUND_FINISH:
                if (gameOver) {
                    setCurrentGameState(GameState.GAME_FINISH);
                    advanceToNextGameState();
                }
                else {
                    setCurrentGameState(GameState.ROUND_START);
                    advanceToNextGameState();
                }
                break;
            case GAME_FINISH:
                break;
        }
        setPlayerInTurn(getNextJogador(getPlayerWithDealerButton()));

    }

    /**
     * Inicia um novo turno.
     */
    public void nextTurn() {
        // TODO Transferir coleta de apostas para o Dealer?
        // mesa.getDealer().coletarApostas();
        if (areAllPlayersChecked()) {
            uncheckAllPlayers();
            currentGameState = currentGameState.getNextState();
            advanceToNextGameState();
        }
        else {
            GameCntrllr.getInstance().getGameView().getPlayerAction();
        }
    }

    /**
     * Define se o jogo acabou.
     * @param gameOver
     */
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    /**
     * Verifica se o jogo acabou.
     * @return boolean
     */
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * Obtem um id unico para uma ação.
     * @return valor de um id
     */
    public int getUniqueActionNumber() {
        return uniqueActionNumber++;
    }
}