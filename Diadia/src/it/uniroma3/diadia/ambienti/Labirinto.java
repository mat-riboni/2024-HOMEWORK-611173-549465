package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Questa classe crea un labirinto di stanze epone al suo interno
 * diversi oggetti.
 * 
 * @author Mattia Riboni
 * @see Attrezzo
 * @see Stanza
 * @version 1HW
 * 
 */
public class Labirinto {
	
	private Stanza stanzaIngresso;
	private Stanza stanzaVincente;
	
	public Labirinto() {
		init();
	}
	
	
	/**
	 * Inizializza il labirinto: crea tutte le stanze e le caratterizza
	 */
	public void init() {
		/* crea gli attrezzi */
    	Attrezzo lanterna = new Attrezzo("lanterna",3);
		Attrezzo osso = new Attrezzo("osso",1);
    	
		/* crea stanze del labirinto */
		Stanza atrio = new Stanza("Atrio");
		Stanza aulaN11 = new Stanza("Aula N11");
		Stanza aulaN10 = new Stanza("Aula N10");
		Stanza laboratorio = new Stanza("Laboratorio Campus");
		Stanza biblioteca = new Stanza("Biblioteca");
		
		/* collega le stanze */
		atrio.impostaStanzaAdiacente("nord", biblioteca);
		atrio.impostaStanzaAdiacente("est", aulaN11);
		atrio.impostaStanzaAdiacente("sud", aulaN10);
		atrio.impostaStanzaAdiacente("ovest", laboratorio);
		aulaN11.impostaStanzaAdiacente("est", laboratorio);
		aulaN11.impostaStanzaAdiacente("ovest", atrio);
		aulaN10.impostaStanzaAdiacente("nord", atrio);
		aulaN10.impostaStanzaAdiacente("est", aulaN11);
		aulaN10.impostaStanzaAdiacente("ovest", laboratorio);
		laboratorio.impostaStanzaAdiacente("est", atrio);
		laboratorio.impostaStanzaAdiacente("ovest", aulaN11);
		biblioteca.impostaStanzaAdiacente("sud", atrio);

        /* pone gli attrezzi nelle stanze */
		aulaN10.addAttrezzo(lanterna);
		atrio.addAttrezzo(osso);
		
		
		/*Imposta l'ingresso e l'uscita del labirinto*/
		this.stanzaIngresso = atrio;
		this.stanzaVincente = biblioteca;

	}
	
	public Stanza getStanzaIngresso() {
		return stanzaIngresso;
	}
	public void setStanzaIngresso(Stanza stanzaIngresso) {
		this.stanzaIngresso = stanzaIngresso;
	}
	
	public Stanza getStanzaVincente() {
		return stanzaVincente;
	}
	public void setStanzaVincente(Stanza stanzaVincente) {
		this.stanzaVincente = stanzaVincente;
	}
	
	

}
