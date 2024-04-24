package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.ComandoPosa;

public class ComandoPosaTest {
	
	private Labirinto labirinto;
	private ComandoPosa posa;
	private Partita partita;
	private Attrezzo attrezzo;
	
	@BeforeEach
	public void setUp() {
		this.labirinto = new Labirinto();
		this.partita = new Partita(labirinto);
		this.attrezzo = new Attrezzo("attrezzo", 1);
		this.posa = new ComandoPosa();
		this.partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
	}
	
	@Test
	public void testEsegui_posaAttrezzoInesistente() {
		String inesistente = "attrezzo inesistente";
		this.posa.setParametro(inesistente);
		this.posa.esegui(this.partita);
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo(inesistente));
	}
	
	@Test
	public void testEsegui_verificaRimozioneDaBorsa() {
		this.posa.setParametro(this.attrezzo.getNome());
		this.posa.esegui(this.partita);
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo(this.attrezzo.getNome()));
	}
	
	@Test
	public void testEsegui_verificaOggettoPosatoInStanza() {
		this.posa.setParametro(this.attrezzo.getNome());
		this.posa.esegui(this.partita);
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo(this.attrezzo.getNome()));
	}

}
