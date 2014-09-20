package br.droidpoker;

import junit.framework.TestCase;

import br.droidpoker.dominio.*;

/**
 * Created by zidenis on 9/20/14.
 */
public class BaralhoTest extends TestCase {
    public void testTamBaralhoBaralho() {
        Baralho brlho = new Baralho();
        assertEquals(brlho.size(), 52);
    }
}
