package it.uniroma3.diadia.ambienti;


import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilder {

	private Labirinto labirinto;
	private Stanza ultimoInserimento;
	private Map<String, Stanza> listaStanze;

	
	public LabirintoBuilder() {
		this.labirinto = new Labirinto();
		this.listaStanze = new HashMap<String, Stanza>();
	}
	
	public Map<String, Stanza> getStanze(){
		return this.listaStanze;
	}
	
	public LabirintoBuilder addStanza(String nome) {
		Stanza stanza = new Stanza(nome);
		this.ultimoInserimento = stanza;
		this.labirinto.getMappaStanze().put(nome, stanza);
		this.listaStanze.put(nome, stanza);
		return this;
	}
	
	public LabirintoBuilder addStanzaIniziale(String nome) {
		this.addStanza(nome);
		this.labirinto.setStanzaIniziale(this.ultimoInserimento);
		return this;
	}
	
	public LabirintoBuilder addStanzaVincente(String nome) {
		this.addStanza(nome);
		this.labirinto.setStanzaVincente(this.ultimoInserimento);
		return this;
	}
	
	public LabirintoBuilder addStanzaMagica(String nome, int soglia) {
		StanzaMagica stanza = new StanzaMagica(nome, soglia);
		this.listaStanze.put(nome, stanza);
		this.labirinto.getMappaStanze().put(nome, stanza);
		this.ultimoInserimento = stanza;
		return this;
	}
	
	public LabirintoBuilder addStanzaBloccata(String nome, String direzione, String nomeSblocco){
		StanzaBloccata stanza = new StanzaBloccata(nome, direzione, nomeSblocco);
		this.listaStanze.put(nome, stanza);
		this.labirinto.getMappaStanze().put(nome, stanza);
		this.ultimoInserimento = stanza;
		return this;
	}
	
	public LabirintoBuilder addStanzaBuia(String nome, String nomeAttrezzoLuminoso) {
		StanzaBuia stanza = new StanzaBuia(nome, nomeAttrezzoLuminoso);
		this.listaStanze.put(nome, stanza);
		this.labirinto.getMappaStanze().put(nome, stanza);
		this.ultimoInserimento = stanza;
		return this;
	}
	
	
	public LabirintoBuilder addAttrezzo(String nomeAttrezzo, int peso) {
		Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
		this.ultimoInserimento.addAttrezzo(attrezzo);
		return this;
	}
	
	public LabirintoBuilder addAdiacenza(String nomeStanzaRiferimento, String nomeStanzaAdiacente, String direzione) {
		Stanza adiacente = this.listaStanze.get(nomeStanzaAdiacente);
		this.listaStanze.get(nomeStanzaRiferimento).impostaStanzaAdiacente(direzione, adiacente);
		return this;
	}

	public Map<String, Stanza> getListaStanze() {
		return this.listaStanze;
	}
	
	public Labirinto getLabirinto() {
		return this.labirinto;
	}
	
	
}
