package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PartitaTest {
	
	private Partita partita;

	@BeforeEach
	void setUp() throws Exception {
		partita = new Partita();
	}

	@Test
	public void testVinta() {
		assertFalse(this.partita.vinta());
	}
	
	@Test
	public void testVintaInStanzaVincente() {
		this.partita.setStanzaCorrente(this.partita.getLabirinto().getStanzaVincente());
		assertTrue(this.partita.vinta());
	}
	
	@Test
	public void isFinita() {
		assertFalse(this.partita.isFinita());
	}
	
	@Test
	public void testIsFinitaDopoSetFinita() {
		this.partita.setFinita();
		assertTrue(this.partita.isFinita());
	}
	
	@Test
	public void testIsFinitaDopoVinta() {
		this.partita.setStanzaCorrente(this.partita.getLabirinto().getStanzaVincente());
		assertTrue(this.partita.isFinita());
	}
	
	@Test
	public void testIsFinitaPerCfu() {
		this.partita.getGiocatore().setCfu(0);
		assertTrue(this.partita.isFinita());
	}
}
