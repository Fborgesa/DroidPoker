package br.droidpoker;

import android.app.Application;
import android.test.InstrumentationTestCase;


import br.droidpoker.dominio.Carta;
import br.droidpoker.dominio.Naipe;
import br.droidpoker.dominio.ValorCarta;

/**
 * Created by zidenis on 9/18/14.
 */
public class CartaTest extends InstrumentationTestCase{
    Carta carta1 = new Carta(ValorCarta.AS, Naipe.PAUS);
    Carta carta2 = new Carta(ValorCarta.DOIS, Naipe.PAUS);
    Carta carta3 = new Carta(ValorCarta.AS, Naipe.COPAS);

    public void testCartaValorMaior() {
        assertEquals(carta1.compareTo(carta2),1);
    }

    public void testCartaIgual() {
        assertEquals(carta1.compareTo(carta3),0);
    }

}
