package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;

class PartitaTest {
	
	private Partita partita;
	private Labirinto labirinto;

	@BeforeEach
	void setUp() throws Exception {
		this.labirinto  = new Labirinto();
		this.partita = new Partita(this.labirinto);
	}

	@Test
	public void testVinta() {
		assertFalse(this.partita.vinta());
	}
	
	@Test
	public void testVinta_inStanzaVincente() {
		this.partita.setStanzaCorrente(this.partita.getLabirinto().getStanzaVincente());
		assertTrue(this.partita.vinta());
	}
	
	@Test
	public void isFinita() {
		assertFalse(this.partita.isFinita());
	}
	
	@Test
	public void testIsFinita_dopoSetFinita() {
		this.partita.setFinita();
		assertTrue(this.partita.isFinita());
	}
	
	@Test
	public void testIsFinita_dopoVinta() {
		this.partita.setStanzaCorrente(this.partita.getLabirinto().getStanzaVincente());
		assertTrue(this.partita.isFinita());
	}
	
	@Test
	public void testIsFinita_perCfu() {
		this.partita.getGiocatore().setCfu(0);
		assertTrue(this.partita.isFinita());
	}
}
