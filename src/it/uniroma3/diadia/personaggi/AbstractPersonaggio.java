package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public abstract class AbstractPersonaggio {

	private String nome;
	
	private String presentazione;
	
	private boolean haSalutato;
	
	public AbstractPersonaggio(String nome, String presenzatione) {
		this.nome = nome;
		this.presentazione = presenzatione;
		this.haSalutato = false;
	}
	
	public String saluta() {
		StringBuilder risposta = new StringBuilder();
		risposta.append(this.getNome() + ".");
		if(!haSalutato) {
			risposta.append(this.presentazione);
		} else {
			risposta.append("Ci siamo già presentati!");
		}
		this.haSalutato = true;
		return risposta.toString();
	}
	
	public abstract String riceviRegalo(Attrezzo attrezzo, Partita partita);
	
	public abstract String agisci(Partita partita);
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPresentazione() {
		return presentazione;
	}

	public void setPresentazione(String presentazione) {
		this.presentazione = presentazione;
	}

	public boolean getHaSalutato() {
		return haSalutato;
	}

	public void setHaSalutato(boolean haSalutato) {
		this.haSalutato = haSalutato;
	}
	
	@Override
	public String toString() {
		return this.getNome();
	}
	
}
