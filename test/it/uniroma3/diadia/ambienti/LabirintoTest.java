package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LabirintoTest {
	
	private Labirinto labirinto;

	@BeforeEach
	void setUp() throws Exception {
		
		this.labirinto = new Labirinto();
		
	}

	@Test
	public void testInit_stanzaIngressoCreata() {
		assertNotNull(this.labirinto.getStanzaIngresso());
	}
	
	@Test
	public void testInit_stanzaUscitaCreata() {
		assertNotNull(this.labirinto.getStanzaVincente());
	}
	
	@Test
	public void testInit_esisteStanzaAdiacenteIngresso() {
		assertNotEquals(0, this.labirinto.getStanzaIngresso().getNumeroStanzeAdiacenti());
	}
	
	@Test
	public void testInit_esisteStanzaAdiacenteUscita() {
		assertNotEquals(0, this.labirinto.getStanzaVincente());
	}

}
