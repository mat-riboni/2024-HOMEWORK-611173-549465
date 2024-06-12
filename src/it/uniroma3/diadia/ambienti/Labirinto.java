package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

/**
 * Questa classe crea un labirinto di stanze epone al suo interno
 * diversi oggetti.
 * 
 * @author Mattia Riboni
 * @see Attrezzo
 * @see Stanza
 * @version 4HW
 * 
 */
public class Labirinto {
	
	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;
	
	public static LabirintoBuilder newBuilder() {
		return new LabirintoBuilder();
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
	

	

	static public class LabirintoBuilder {

		private Labirinto labirinto;
		private Stanza ultimoInserimento;
		private Map<String, Stanza> nome2stanza;

		
		public LabirintoBuilder() {
			this.labirinto = new Labirinto();
			this.nome2stanza = new HashMap<String, Stanza>();
		}
		
		public LabirintoBuilder addStanza(String nome) {
			Stanza stanza = new Stanza(nome);
			this.ultimoInserimento = stanza;
			this.nome2stanza.put(nome, stanza);
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
			this.nome2stanza.put(nome, stanza);
			this.ultimoInserimento = stanza;
			return this;
		}
		
		public LabirintoBuilder addStanzaBloccata(String nome, String direzione, String nomeSblocco){
			StanzaBloccata stanza = new StanzaBloccata(nome, direzione, nomeSblocco);
			this.nome2stanza.put(nome, stanza);
			this.ultimoInserimento = stanza;
			return this;
		}
		
		public LabirintoBuilder addStanzaBuia(String nome, String nomeAttrezzoLuminoso) {
			StanzaBuia stanza = new StanzaBuia(nome, nomeAttrezzoLuminoso);
			this.nome2stanza.put(nome, stanza);
			this.ultimoInserimento = stanza;
			return this;
		}
		
		
		public LabirintoBuilder addAttrezzo(String nomeAttrezzo, int peso) {
			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
			this.ultimoInserimento.addAttrezzo(attrezzo);
			return this;
		}
		
		public LabirintoBuilder addAttrezzo(String nomeStanza, String nomeAttrezzo, int peso) {
			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
			this.nome2stanza.get(nomeStanza).addAttrezzo(attrezzo);
			return this;
		}
		
		public LabirintoBuilder addAdiacenza(String nomeStanzaRiferimento, String nomeStanzaAdiacente, String direzione) {
			Stanza adiacente = this.nome2stanza.get(nomeStanzaAdiacente);
			this.nome2stanza.get(nomeStanzaRiferimento).impostaStanzaAdiacente(direzione, adiacente);
			return this;
		}
		
		@SuppressWarnings("deprecation")
		public LabirintoBuilder addPersonaggio(String tipo, String nomePersonaggio, String presentazione) {
			StringBuilder tipoPersonaggio = new StringBuilder();
			tipoPersonaggio.append("it.uniroma3.diadia.personaggi.");
			tipoPersonaggio.append(Character.toUpperCase(tipo.charAt(0)));
			tipoPersonaggio.append(tipo.substring(1));
			AbstractPersonaggio personaggio = null;
			try {
				personaggio = (AbstractPersonaggio) Class.forName(tipoPersonaggio.toString()).newInstance();
				personaggio.setNome(nomePersonaggio);
				personaggio.setPresentazione(presentazione);
			} catch (Exception e) {
				
			}
			this.ultimoInserimento.setPersonaggio(personaggio);
			return this;
		}
		@SuppressWarnings("deprecation")
		public LabirintoBuilder addPersonaggio(String tipo, String nomePersonaggio, String presentazione, String nomeStanza) {
			StringBuilder tipoPersonaggio = new StringBuilder();
			tipoPersonaggio.append("it.uniroma3.diadia.personaggi.");
			tipoPersonaggio.append(Character.toUpperCase(tipo.charAt(0)));
			tipoPersonaggio.append(tipo.substring(1));
			AbstractPersonaggio personaggio = null;
			try {
				personaggio = (AbstractPersonaggio) Class.forName(tipoPersonaggio.toString()).newInstance();
				personaggio.setNome(nomePersonaggio);
				personaggio.setPresentazione(presentazione);
			} catch (Exception e) {
				
			}
			this.nome2stanza.get(nomeStanza).setPersonaggio(personaggio);
			return this;
		}
		
		public Labirinto getLabirinto() {
			return this.labirinto;
		}
		
		public Map<String, Stanza> getNome2Stanza(){
			return this.nome2stanza;
		}
		
	}


}
