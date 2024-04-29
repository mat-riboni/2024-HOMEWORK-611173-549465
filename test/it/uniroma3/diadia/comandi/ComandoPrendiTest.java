package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.ComandoPrendi;

public class ComandoPrendiTest {

	private Labirinto labirinto;
	private ComandoPrendi prendi;
	private Partita partita;
	private Attrezzo attrezzo;
	private IO io;

	@BeforeEach
	public void setUp() {
		this.labirinto = new Labirinto();
		this.partita = new Partita(labirinto);
		this.attrezzo = new Attrezzo("attrezzo", 1);
		this.io = new IOConsole();
		this.prendi = new ComandoPrendi(this.io);
		this.partita.getStanzaCorrente().addAttrezzo(attrezzo);
	}
	
	@Test
	public void testEsegui_prendiAttrezzoNonPresente() {
		String nomeAttrezzoInesistente = "inesistente";
		this.prendi.setParametro(nomeAttrezzoInesistente);
		this.prendi.esegui(partita);
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo(nomeAttrezzoInesistente));
	}
	
	@Test
	public void testEsegui_attrezzoMessoInBorsa() {
		this.prendi.setParametro(this.attrezzo.getNome());
		this.prendi.esegui(partita);
		assertTrue(this.partita.getGiocatore().getBorsa().hasAttrezzo(this.attrezzo.getNome()));
	}
	
	@Test
	public void testEsegui_attrezzoRimossoDaStanza() {
		this.prendi.setParametro(this.attrezzo.getNome());
		this.prendi.esegui(partita);
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo(this.attrezzo.getNome()));
	}

}
