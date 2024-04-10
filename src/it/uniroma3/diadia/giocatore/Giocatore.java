package it.uniroma3.diadia.giocatore;

/**
 * Modella un giocatore di una parita.
 * Il giocatore possiede dei CFU e una borsa.
 * 
 * @author Mattia Riboni
 * @see Borsa
 * @version HW1
 */


public class Giocatore {
	
	static final private int CFU_INIZIALI = 20;

	private int cfu;
	private Borsa borsa;
	
	public Giocatore() {
		this.cfu = CFU_INIZIALI;
		this.borsa = new Borsa();
	}

	public int getCfu() {
		return cfu;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;
	}

	public Borsa getBorsa() {
		return borsa;
	}

	
}
