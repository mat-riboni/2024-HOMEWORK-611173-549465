package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;

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
	
	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;
	private Map<String, Stanza> mappaStanze;
	
	public Labirinto() {
		this.mappaStanze = new HashMap<String, Stanza>();
	}
	
	public Stanza getStanzaIniziale() {
		return stanzaIniziale;
	}
	public void setStanzaIniziale(Stanza stanzaIngresso) {
		this.stanzaIniziale = stanzaIngresso;
	}
	
	public Stanza getStanzaVincente() {
		return stanzaVincente;
	}
	public void setStanzaVincente(Stanza stanzaVincente) {
		this.stanzaVincente = stanzaVincente;
	}
	
	public Map<String, Stanza> getMappaStanze() {
		return this.mappaStanze;
	}
	

}
