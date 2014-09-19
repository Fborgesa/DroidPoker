package br.droidpoker;


import junit.framework.TestCase;

import br.droidpoker.dominio.Carta;
import br.droidpoker.dominio.Naipe;
import br.droidpoker.dominio.ValorCarta;

/**
 * Created by zidenis on 9/18/14.
 */
public class CartaTest extends TestCase{
    Carta carta1 = new Carta(ValorCarta.AS, Naipe.PAUS);
    Carta carta2 = new Carta(ValorCarta.DOIS, Naipe.PAUS);
    Carta carta3 = new Carta(ValorCarta.AS, Naipe.COPAS);

    public void testComparacaoDeCartas() {
        assertEquals(carta1.compareTo(carta2),1);
        assertEquals(carta2.compareTo(carta3),-1);
        assertEquals(carta3.compareTo(carta1),0);
    }
}
