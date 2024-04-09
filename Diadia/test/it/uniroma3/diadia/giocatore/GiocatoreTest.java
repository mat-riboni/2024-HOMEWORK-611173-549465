package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.giocatore.Giocatore;

class GiocatoreTest {
	
	private Giocatore giocatore;

	@BeforeEach
	public void setup() {
		this.giocatore = new Giocatore();
	}

	@Test
	public void testGetCfu() {
		assertNotEquals(0, this.giocatore.getCfu());
	}
	
	@Test
	public void testGetBorsa() {
		assertNotNull(this.giocatore.getBorsa());
	}

}
