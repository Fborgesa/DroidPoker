package br.droidpoker.domain;

import java.util.Collections;
import java.util.Stack;

public class Baralho {

	private Stack<Carta> brlho = new Stack<Carta>();

    /**
     * Construtor do baralho
     */

	public Baralho() {
        for (Naipe npe: Naipe.values()) {
            for (TipoCarta tpCrta: TipoCarta.values()) {
                this.brlho.push(new Carta(tpCrta, npe));
            }
        }
        embaralhar();
    }

    /**
     *  Metodo de retorno do tamanho do baralho
     */
    public int size() {
        return this.brlho.size();
    }

    /**
     * Metodo para embaralhar
     */
    private void embaralhar() {
        Collections.shuffle(this.brlho);
    }

    /*
     * Metodo que usa a funcao pop de uma lista para retirar
     * a primeira carta do baralho
    */
    public Carta pegarDoBaralho(){
       return this.brlho.pop();
    }
}

