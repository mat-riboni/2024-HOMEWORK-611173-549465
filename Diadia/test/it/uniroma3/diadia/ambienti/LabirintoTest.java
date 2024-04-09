package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;

class LabirintoTest {
	
	private Labirinto labirinto;

	@BeforeEach
	void setUp() throws Exception {
		
		this.labirinto = new Labirinto();
		
	}

	@Test
	public void testInitStanzaIngressoCreata() {
		assertNotNull(this.labirinto.getStanzaIngresso());
	}
	
	@Test
	public void testInitStanzaUscitaCreata() {
		assertNotNull(this.labirinto.getStanzaVincente());
	}
	
	@Test
	public void testInitEsisteStanzaAdiacenteIngresso() {
		assertNotEquals(0, this.labirinto.getStanzaIngresso().getNumeroStanzeAdiacenti());
	}
	
	@Test
	public void testInitEsisteStanzaAdiacenteUscita() {
		assertNotEquals(0, this.labirinto.getStanzaVincente());
	}

}
