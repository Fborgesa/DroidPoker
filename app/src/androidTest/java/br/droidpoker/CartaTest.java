package br.droidpoker;

import junit.framework.TestCase;

import br.droidpoker.dominio.Carta;
import br.droidpoker.dominio.Naipe;
import br.droidpoker.dominio.TipoCarta;

/**
 * Created by zidenis on 9/18/14.
 */
public class CartaTest extends TestCase{
    Carta carta1 = new Carta(TipoCarta.AS, Naipe.PAUS);
    Carta carta2 = new Carta(TipoCarta.DOIS, Naipe.PAUS);
    Carta carta3 = new Carta(TipoCarta.AS, Naipe.COPAS);

    public void testComparacaoDeCartas() {
        assertEquals(carta1.compareTo(carta2),1);
        assertEquals(carta2.compareTo(carta3),-1);
        assertEquals(carta3.compareTo(carta1),0);
    }

    public void testValorCarta() {
        assertEquals(carta1.getNome(), TipoCarta.AS);
        assertEquals(carta2.getNome().getValor(), 2);
        assertEquals(carta3.getNome().getValor(), carta1.getNome().getValor());
        assertEquals(carta2.getNome().getNext(), TipoCarta.TRES);
        assertEquals(carta1.getNome().getNext(), TipoCarta.DOIS);
        assertEquals(carta1.getNome().getNext(), carta2.getNome());
        assertEquals(carta1.getNome().getNext(), carta3.getNome().getNext());
        assertEquals(TipoCarta.DEZ.getNext(),TipoCarta.VALETE);
    }
}
