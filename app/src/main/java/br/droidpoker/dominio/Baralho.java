package br.droidpoker.dominio;

import java.util.Collections;
import java.util.Stack;

public class Baralho {

	private Stack<Carta> brlho = new Stack<Carta>();

	public Baralho() {
        for (Naipe npe: Naipe.values()) {
            for (TipoCarta tpCrta: TipoCarta.values()) {
                this.brlho.push(new Carta(tpCrta, npe));
            }
        }
        embaralhar(); // faz sentido criar um baralho não embaralhado?
    }

    public int size() {
        return this.brlho.size();
    }

    private void embaralhar() {
        Collections.shuffle(this.brlho);
    }

    public Carta pegarDoBaralho(){
        return this.brlho.pop();
    }
}

