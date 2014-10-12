package br.droidpoker;

import junit.framework.TestCase;

import br.droidpoker.dominio.Carta;
import br.droidpoker.dominio.Mao;
import br.droidpoker.dominio.Naipe;
import br.droidpoker.dominio.TipoCarta;

public class MaoTest extends TestCase {
    Carta carta1;
    Carta carta2;
    Carta carta3;
    Carta carta4;
    Carta carta5;
    Carta carta6;
    Carta carta7;
    Carta carta8;
    Mao maoJogador1;
    Mao maoJogador2;
    Mao maoJogador3;
    Mao maoJogador4;

    protected void setUp() {
        carta1 = new Carta(TipoCarta.AS, Naipe.PAUS);
        carta2 = new Carta(TipoCarta.DOIS, Naipe.PAUS);
        carta3 = new Carta(TipoCarta.AS, Naipe.COPAS);
        carta4 = new Carta(TipoCarta.DEZ, Naipe.OUROS);
        carta5 = new Carta(TipoCarta.REI, Naipe.ESPADAS);
        carta6 = new Carta(TipoCarta.DAMA, Naipe.OUROS);
        carta7 = new Carta(TipoCarta.DEZ, Naipe.COPAS);
        carta8 = new Carta(TipoCarta.DEZ, Naipe.PAUS);
        maoJogador1 = new Mao();
        maoJogador2 = new Mao();
        maoJogador3 = new Mao();
        maoJogador4 = new Mao();
        try {
            maoJogador1.addCarta(carta1);
            maoJogador1.addCarta(carta2);
            maoJogador2.addCarta(carta3);
            maoJogador2.addCarta(carta4);
            maoJogador3.addCarta(carta5);
            maoJogador3.addCarta(carta6);
            maoJogador4.addCarta(carta7);
            maoJogador4.addCarta(carta8);
        }
        catch (Exception e) {
            fail();
        }
    }

    // Testa distribuição de cartas a um jogador
    public void testAddCartasMaoJogador() {
        Mao mao = new Mao();
        try {
            assertEquals(mao.numCartas(), 0);
            mao.addCarta(carta1);
            assertEquals(mao.numCartas(), 1);
            mao.addCarta(carta2);
            assertEquals(mao.numCartas(), 2);
            mao.addCarta(carta3);
            fail("Exception : uma mao nao deve ter mais de 2 cartas");
        }
        catch (Exception e) {
            assertTrue(true);
        }
    }


    public void testMaoJogador() {
        Carta[] cartas = maoJogador1.getCartas();
        assertEquals(cartas[0],carta1);
        assertEquals(cartas[1],carta2);
        assertNotSame(cartas[0],carta2);
    }

    // Testa heurística para avaliação (determinação do score) da mão do jogador
    public void testScoreMao() {
        assertEquals(maoJogador1.getScore(),24);
        assertEquals(maoJogador2.getScore(),24);
        assertTrue(maoJogador1.getScore() == maoJogador2.getScore());
        assertEquals(maoJogador3.getScore(),28);
        assertTrue(maoJogador1.getScore() < maoJogador3.getScore());
        assertEquals(maoJogador4.getScore(),30);
        assertEquals(new Mao().getScore(),0);
    }

    // Testa mecanismo de comparação de mãos entre jogadores
    public void testComparacaoMaos() {
        assertEquals(maoJogador1.compareTo(new Mao()),1);
        assertEquals(maoJogador1.compareTo(maoJogador2), 0);
        assertEquals(maoJogador1.compareTo(maoJogador3), -1);
        assertEquals(maoJogador4.compareTo(maoJogador3), 1);
    }
}
