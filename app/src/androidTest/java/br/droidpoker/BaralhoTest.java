package br.droidpoker;

import junit.framework.TestCase;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;

import br.droidpoker.dominio.*;

/**
 * Created by zidenis on 9/20/14.
 */
public class BaralhoTest extends TestCase {

    public void testTamBaralhoBaralho() {
        Baralho brlho = new Baralho();
        assertEquals(brlho.size(), 52);
    }

    public void testCartasDiferentes(){

        Baralho baralhoTeste = new Baralho();
        ArrayList<Carta> baralhoLista = new ArrayList<Carta>();

        do {
            //assertNotSame(cas,baralhoTeste.pegarDoBaralho());
            baralhoLista.add(baralhoTeste.pegarDoBaralho());
        } while (baralhoTeste.size() != 0);

        Collections.sort(baralhoLista);
        Log.d("jeff",baralhoLista.toString());
        /*
        Carta cas = baralhoLista.get(0);
        Carta caseCompare = baralhoLista.get(1);

        for (int i = 2; i < 25;i++){
            assertNotSame(cas,caseCompare);
            cas = baralhoLista.get(i);
            int j = i;
            caseCompare = baralhoLista.get(j++);
        }*/
    }

    /*public void estaEmbaralhadoTest() {
        Baralho baralho1 = new Baralho();
        Baralho baralho2 = null;

        baralho2 = baralho1.;
        assertNotNull(Baralho, baralho2);
        baralho1= baralho1.embaralhar();

        assertNotSame(baralho1,baralho2);
    }*/
}