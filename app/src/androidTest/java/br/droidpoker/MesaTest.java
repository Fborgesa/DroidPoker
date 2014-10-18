package br.droidpoker;
import junit.framework.TestCase;
import br.droidpoker.domain.Carta;
import br.droidpoker.domain.Humano;
import br.droidpoker.domain.Mao;
import br.droidpoker.domain.Naipe;
import br.droidpoker.domain.TipoCarta;
import br.droidpoker.domain.Mesa;

/**
 * Created by Jefferson on 18/10/2014.
 */
public class MesaTest extends TestCase{
    Humano play1 = new Humano(1,"Denis",1000);
    Humano play2 = new Humano(1,"Jeff",1000);
    Humano play3 = new Humano(1,"Tata",1000);
    Mesa mesaTest = Mesa.getInstance();

    public void isSingleton (){
        assertNotNull("tem instância",Mesa.getInstance());
        assertSame("é a mesma instância", Mesa.getInstance(), Mesa.getInstance());
    }

    public void AddIsWorking (){
        assertEquals(mesaTest.getJogadores().size(),0);
        mesaTest.addJogador(play1);
        assertEquals(mesaTest.getJogadores().size(),1);
    }

    public void GetNextIsWorking () {
        mesaTest.addJogador(play1);
        mesaTest.addJogador(play2);
        assertNotSame(mesaTest.getJogadores().get(0),mesaTest.getNextJogador(mesaTest.getJogadores().get(0)));
    }

    public void GetPreviousIsWorking () {
        mesaTest.addJogador(play1);
        mesaTest.addJogador(play2);
        assertNotSame(mesaTest.getJogadores().get(1),mesaTest.getPreviousJogador(mesaTest.getJogadores().get(1)));
    }

    public void SetAndGetPlayerWithDealerButtonIsWorking() {
        mesaTest.setPlayerWithDealerButton(play1);
        assertEquals(play1,mesaTest.getPlayerWithDealerButton());
    }

    public void passTheButtonIsWorking(){
        mesaTest.addJogador(play1);
        mesaTest.addJogador(play2);
        mesaTest.setPlayerWithDealerButton(play1);
        mesaTest.passTheButton();
        assertEquals(play2,mesaTest.getPlayerWithDealerButton());
    }

    public void SetAndGetPlayerWithTurnTokenIsWorking() {
        mesaTest.setPlayerInTurn(play1);
        assertEquals(play1,mesaTest.getPlayerInTurn());
    }

    public void passTheTurnTokenIsWorking(){
        mesaTest.addJogador(play1);
        mesaTest.addJogador(play2);
        mesaTest.setPlayerInTurn(play1);
        mesaTest.passTheTurnToken();
        assertEquals(play2,mesaTest.getPlayerInTurn());
    }

    public void AddCartasComunitariasIsWorking(){
        assertEquals(mesaTest.listCartasComunitaria().size(),0);
        mesaTest.addCartaComunitaria();
        assertEquals(mesaTest.listCartasComunitaria().size(),1);
    }

    public void getActivePoteIsWorking(){
        assertNotNull(mesaTest.getActivePote());
    }

    public void SetAndGetBLindValueIsWorking(){
        mesaTest.setBlindValue(35);
        assertEquals(mesaTest.getBlindValue(),35);
    }


}
