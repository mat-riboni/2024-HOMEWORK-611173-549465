package it.uniroma3.diadia.comandi;


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;
import it.uniroma3.diadia.Partita;

public class ComandoVaiTest {
	
	private Labirinto bilocale;
	private ComandoVai vai;
	
	@Before
	public void setUp() {
		
		this.vai = new ComandoVai();
		
		this.bilocale = new LabirintoBuilder()
				.addStanzaIniziale("ingresso")
				.addStanzaVincente("uscita")
				.addAdiacenza("ingresso", "uscita", "nord")
				.getLabirinto();
	}
	
	@Test
	public void testEsegui_stanzaCorrenteCambiata() {
		Partita p = new Partita(bilocale);
		this.vai.setParametro("nord");
		this.vai.esegui(p);
		assertEquals("uscita", p.getStanzaCorrente().getNome());
	}
	

	@Test
	public void testEsegui_direzioneInesistente() {
		Partita p = new Partita(bilocale);
		this.vai.setParametro("direzione inesistente");
		this.vai.esegui(p);
		assertEquals("ingresso", p.getStanzaCorrente().getNome());
	}
	
	 @Test
	 public void testEsegui_parametroNull() {
		 Partita p = new Partita(this.bilocale);
		 this.vai.setParametro(null);
		 this.vai.esegui(p);
		 assertEquals("ingresso", p.getStanzaCorrente().getNome());
		 
	 }

}
