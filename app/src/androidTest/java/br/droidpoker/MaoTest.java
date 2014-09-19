package br.droidpoker;

import junit.framework.TestCase;

import br.droidpoker.dominio.*;

/**
 * Created by zidenis on 9/19/14.
 */
public class MaoTest extends TestCase {
    Carta carta1 = new Carta(ValorCarta.AS, Naipe.PAUS);
    Carta carta2 = new Carta(ValorCarta.DOIS, Naipe.PAUS);
    Carta carta3 = new Carta(ValorCarta.AS, Naipe.COPAS);
    Mao maoJogador1 = new Mao();

    public void testAddCartasMaoJogador() {
        try {
            assertEquals(maoJogador1.numCartas(), 0);
            maoJogador1.addCarta(carta1);
            assertEquals(maoJogador1.numCartas(), 1);
            maoJogador1.addCarta(carta2);
            assertEquals(maoJogador1.numCartas(), 2);
            maoJogador1.addCarta(carta3);
            fail("Exception : uma mao nao deve ter mais de 2 cartas");
        }
        catch (Exception e) {
            assertTrue(true);
        }
    }
    public void testMaoJogador() {
        try {
            maoJogador1.addCarta(carta1);
            maoJogador1.addCarta(carta2);
            Carta[] cartas = maoJogador1.getCartas();
            assertEquals(cartas[0],carta1);
            assertEquals(cartas[1],carta2);
            assertNotSame(cartas[0],carta2);
        }
        catch (Exception e) {
            fail();
        }
    }
}
