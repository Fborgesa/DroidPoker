package br.droidpoker;

import junit.framework.TestCase;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;

import br.droidpoker.dominio.*;

public class BaralhoTest extends TestCase {

    // Testa se um novo baralho possui 52 cartas
    public void testTamBaralho() {
        Baralho brlho = new Baralho();
        assertEquals(brlho.size(), 52);
    }

    // Verificar se todas as cartas do baralho são diferentes
    public void testCartasDiferentes(){
        Baralho baralhoTeste = new Baralho();
        ArrayList<Carta> baralhoLista = new ArrayList<Carta>();

        do {
            baralhoLista.add(baralhoTeste.pegarDoBaralho());
        } while (baralhoTeste.size() > 0);

        Log.d("unb",baralhoLista.toString()); // mostra baralho criado
        Collections.sort(baralhoLista);
        Log.d("unb",baralhoLista.toString()); // mostra baralho ordenado

        for (int i = 0; i < baralhoLista.size()-2; i++) {
            for (int j = i+1; j < baralhoLista.size()-1; j++) {
                Carta c1 = baralhoLista.get(i);
                Carta c2 = baralhoLista.get(j);
                assertFalse(c1.toString() + " diferente de " + c2.toString(), c1.equals(c2));
            }
        }
    }
}