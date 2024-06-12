package it.uniroma3.diadia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.io.StringReader;

import org.junit.Test;

import it.uniroma3.diadia.ambienti.Labirinto;

public class CaricatoreLabirintoTest {
	
	private CaricatoreLabirinto caricatore;

	private final String monolocale = "Stanze:N10\n" + 
									  "Inizio:N10\n" +
									  "Vincente:N10\n"+
									  "Attrezzi:\n" +
									  "Uscite:";
	
	private final String labirinto = "Stanze:biblioteca,N10,N11\n"+
									 "Inizio:N10\n" + 
									 "Vincente:N11\n" + 
									 "Attrezzi:martello 10 biblioteca,pinza 2 N10\n" + 
									 "Uscite:biblioteca sud N11,biblioteca nord N10 ";
	
	@Test
	public void testCarica_monolocale() throws FormatoFileNonValidoException {
		this.caricatore = new CaricatoreLabirinto(new StringReader(this.monolocale));
		this.caricatore.carica();
		assertNotEquals(0, caricatore.getBuilder().getNome2Stanza().size());
		assertEquals("N10", caricatore.getBuilder().getLabirinto().getStanzaIniziale().getNome());
		assertEquals("N10", caricatore.getBuilder().getLabirinto().getStanzaVincente().getNome());
		
	}
	
	@Test
	public void testCarica_labirinto() throws FormatoFileNonValidoException{
		this.caricatore = new CaricatoreLabirinto(new StringReader(this.labirinto));
		this.caricatore.carica();
		assertNotEquals(0, caricatore.getBuilder().getNome2Stanza().size());
		assertEquals("N10", caricatore.getBuilder().getLabirinto().getStanzaIniziale().getNome());
		assertEquals("N11", caricatore.getBuilder().getLabirinto().getStanzaVincente().getNome());
		assertTrue("martello", caricatore.getBuilder().getNome2Stanza().get("biblioteca").hasAttrezzo("martello"));
		assertEquals("N11", caricatore.getBuilder().getNome2Stanza().get("biblioteca").getMapStanzeAdiacenti().get("sud").getNome());
		
	}
	
}
