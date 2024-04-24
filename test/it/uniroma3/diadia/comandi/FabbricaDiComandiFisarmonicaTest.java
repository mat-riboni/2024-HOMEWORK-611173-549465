package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;

public class FabbricaDiComandiFisarmonicaTest {

	FabbricaDiComandiFisarmonica factory;
	
	@BeforeEach
	public void setUp() {
		this.factory = new FabbricaDiComandiFisarmonica();
	}
	
	@Test
	public void testCostruisciComando_vai() {
		assertEquals("vai", this.factory.costruisciComando("vai").getNome());
	}
	
	@Test
	public void testCostruisciComando_prendi() {
		assertEquals("prendi", this.factory.costruisciComando("prendi").getNome());
	}
	
	@Test
	public void testCostruisciComando_posa() {
		assertEquals("posa", this.factory.costruisciComando("posa").getNome());
	}
	
	@Test
	public void testCostruisciComando_fine() {
		assertEquals("fine", this.factory.costruisciComando("fine").getNome());
	}
	
	@Test	
	public void testCostruisciComando_guarda() {
		assertEquals("guarda", this.factory.costruisciComando("guarda").getNome());
	}
	
	@Test
	public void testCostruisciComando_nonValido() {
		assertEquals("comando non valido", this.factory.costruisciComando("non valido").getNome());
	}
	
	

	
}
