package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LabirintoTest {
	
	private Labirinto labirinto;

	@BeforeEach
	void setUp() throws Exception {
		
		this.labirinto = new LabirintoBuilder().
				addStanzaIniziale("inizio").
				addStanzaVincente("fine").
				addAdiacenza("inizio", "fine", "nord").
				addAdiacenza("fine", "inizio", "sud").
				getLabirinto();	
	}

	@Test
	public void testInit_stanzaIngressoCreata() {
		assertNotNull(this.labirinto.getStanzaIniziale());
	}
	
	@Test
	public void testInit_stanzaUscitaCreata() {
		assertNotNull(this.labirinto.getStanzaVincente());
	}
	
	@Test
	public void testInit_esisteStanzaAdiacenteIngresso() {
		assertNotEquals(0, this.labirinto.getStanzaIniziale().getNumeroStanzeAdiacenti());
	}
	
	@Test
	public void testInit_esisteStanzaAdiacenteUscita() {
		assertNotEquals(0, this.labirinto.getStanzaVincente());
	}

}
