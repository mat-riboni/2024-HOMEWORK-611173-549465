package it.uniroma3.diadia.giocatore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.attrezzi.ComparatoreAttrezziPerPeso;
import it.uniroma3.diadia.attrezzi.ComparatoreAttrezziPerPesoNome;

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
	private List<Attrezzo> attrezzi;
	private int pesoMax;


	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}


	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new ArrayList<Attrezzo>();
	}


	/**
	 * Aggiunge, se possibile, un oggetto nella borsa
	 * @param attrezzo
	 * @return true se l'oggetto viene aggiunto correttamente
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		return this.attrezzi.add(attrezzo);
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
		for(Attrezzo attrezzo: this.attrezzi) {
			if(attrezzo.getNome().equals(nomeAttrezzo)) {
				return attrezzo;
			}
		}
		return null;
	}
	
	public List<Attrezzo> getAttrezzi() {
		return this.attrezzi;
	}

	public int getPeso() {
		int peso = 0;
		for (Attrezzo attrezzo : attrezzi)
			peso += attrezzo.getPeso();

		return peso;
	}
	
	public boolean isEmpty() {
		return this.attrezzi.isEmpty();
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
		for (Attrezzo attrezzo : this.attrezzi){
			if(attrezzo.getNome().equals(nomeAttrezzo)) {
				this.attrezzi.remove(attrezzo);
				return attrezzo;
			}
		}
		return null;
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();

		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			for (Attrezzo attrezzo : this.attrezzi)
				s.append(attrezzo.toString()+" ");
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}
	
	
	public List<Attrezzo> getContenutoOrdinatoPerPeso(){
		ComparatoreAttrezziPerPeso comp = new ComparatoreAttrezziPerPeso();
		List<Attrezzo> attrezziOrdinatiPerPeso = new ArrayList<Attrezzo>(this.attrezzi);
		Collections.sort(attrezziOrdinatiPerPeso, comp);
		return attrezziOrdinatiPerPeso;
				
	}
	
	public SortedSet<Attrezzo> getContenutoOrdniatoPerNome(){
		SortedSet<Attrezzo> attrezziOrdinatiPerNome = new TreeSet<Attrezzo>(this.attrezzi);
		return attrezziOrdinatiPerNome;
	}
	
	public Map<Integer, Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		Map<Integer, Set<Attrezzo>> attrezziRaggruppatiPerPeso = new HashMap<Integer, Set<Attrezzo>>();
		for(Attrezzo attrezzo : this.attrezzi) {
			if(!attrezziRaggruppatiPerPeso.containsKey(attrezzo.getPeso())) {
				Set<Attrezzo> setNuovoPeso = new HashSet<Attrezzo>();
				setNuovoPeso.add(attrezzo);
				attrezziRaggruppatiPerPeso.put(attrezzo.getPeso(), setNuovoPeso);
			} else {
				attrezziRaggruppatiPerPeso.get(attrezzo.getPeso()).add(attrezzo);
			}
		}
		
		return attrezziRaggruppatiPerPeso;
	}
	
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso(){
		ComparatoreAttrezziPerPesoNome comp = new ComparatoreAttrezziPerPesoNome();
		SortedSet<Attrezzo> attrezziOrdinatiPerPesoNome = new TreeSet<Attrezzo>(comp);
		attrezziOrdinatiPerPesoNome.addAll(this.attrezzi);
		return attrezziOrdinatiPerPesoNome;
		
	}
	
}
