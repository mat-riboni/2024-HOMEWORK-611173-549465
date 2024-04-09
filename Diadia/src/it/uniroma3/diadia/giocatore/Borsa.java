package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Questa classe modella la borsa di un giocatore.
 * La borsa ha un numero massimo di oggetti che può
 * contenere e un massimale di peso che può essere raggiunto.
 * Se si raggiunge uno di questi due limiti, si dovrà rimuovere
 * un opportuno oggetto dalla borsa prima di poterne inserire un altro.
 * 
 * @author docente di POO / Mattia Riboni
 * @see Attrezzo
 * @version HW1
 */

public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private Attrezzo[] attrezzi;
	private int numeroAttrezzi;
	private int pesoMax;


	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}


	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new Attrezzo[10]; // speriamo bastino...
		this.numeroAttrezzi = 0;
	}


	/**
	 * Aggiunge, se possibile, un oggetto nella borsa
	 * @param attrezzo
	 * @return true se l'oggetto viene aggiunto correttamente
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		if (this.numeroAttrezzi==10)
			return false;
		this.attrezzi[this.numeroAttrezzi] = attrezzo;
		this.numeroAttrezzi++;
		return true;
	}


	public int getPesoMax() {
		return pesoMax;
	}


	/**
	 * Cerca un attrezzo nella borsa
	 * @param nomeAttrezzo
	 * @return l'attrezzo cercato
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		for (int i= 0; i<this.numeroAttrezzi; i++)
			if (this.attrezzi[i].getNome().equals(nomeAttrezzo))
				a = attrezzi[i];

		return a;
	}
	
	public Attrezzo[] getAttrezzi() {
		return this.attrezzi;
	}

	public int getPeso() {
		int peso = 0;
		for (int i = 0; i < this.numeroAttrezzi; i++)
			peso += this.attrezzi[i].getPeso();

		return peso;
	}
	
	public boolean isEmpty() {
		return this.numeroAttrezzi == 0;
	}
	
	
	/**
	 * Verifica se l'attrezzo è presente nella borsa
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo è nella borsa
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}
	
	
	/**
	 * Rimuove un attrezzo dalla borsa
	 * @param nomeAttrezzo
	 * @return l'attrezzo rimosso
	 */
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		for (int i = 0; i < this.numeroAttrezzi; i++) {
			if(this.attrezzi[i].getNome().equals(nomeAttrezzo)) {
				a = this.attrezzi[i];
				this.attrezzi[i] = this.attrezzi[this.numeroAttrezzi - 1];
				this.attrezzi[this.numeroAttrezzi - 1] = null;
				this.numeroAttrezzi--;
				return a;
			}
		}
		return a;
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();

		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			for (int i= 0; i<this.numeroAttrezzi; i++)
				s.append(attrezzi[i].toString()+" ");
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}
	
}
