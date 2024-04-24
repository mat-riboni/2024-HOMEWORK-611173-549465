package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaTest {
	
	private Stanza stanzaNormale;
	private Stanza stanzaAdiacenteSud;
	private Attrezzo attrezzo1;

	@BeforeEach
	public void setUp(){
		
		this.stanzaAdiacenteSud = new Stanza("adiacenteSud");
		this.stanzaNormale = new Stanza("normale");
		this.attrezzo1 = new Attrezzo("attrezzo1", 3);
	
		this.stanzaNormale.impostaStanzaAdiacente("sud", stanzaAdiacenteSud);
		this.stanzaNormale.addAttrezzo(attrezzo1);
		
	}
	
	

	@Test
	public void testImpostaStanzaAdiacente() {
		assertEquals(this.stanzaAdiacenteSud, this.stanzaNormale.getStanzaAdiacente("sud"));
	}
	
	@Test
	public void testImpostaStanzaAdicente_aggiornaEsistente() {
		Stanza stanzaAdiacenteSudNuova = new Stanza("adiacenteSudNuova");
		this.stanzaNormale.impostaStanzaAdiacente("sud", stanzaAdiacenteSudNuova);
		assertEquals(stanzaAdiacenteSudNuova, this.stanzaNormale.getStanzaAdiacente("sud"));
	}
	
	@Test
	public void testImpostaStanzaAdiacente_inDrezioneNonValida() {
		Stanza adiacenteNord = new Stanza("adiacenteNord");
		Stanza adiacenteEst = new Stanza("adiacenteEst");
		Stanza adiacenteOvest = new Stanza("adiacenteOvest");
		Stanza stanzaDirezioneNonValida = new Stanza("direzioneNonValida");
		
		this.stanzaNormale.impostaStanzaAdiacente("nord", adiacenteNord);
		this.stanzaNormale.impostaStanzaAdiacente("est", adiacenteEst);
		this.stanzaNormale.impostaStanzaAdiacente("ovest", adiacenteOvest);
		
		this.stanzaNormale.impostaStanzaAdiacente("sud-ovest", stanzaDirezioneNonValida);
		assertNull(stanzaNormale.getStanzaAdiacente("sud-ovest"));
	}
	
	@Test
	public void testAddAttrezzo() {
		assertTrue(this.stanzaNormale.hasAttrezzo("attrezzo1"));
	}
	
	@Test
	public void testAddAttrezzo_secondoAttrezzo() {
		Attrezzo attrezzo2 = new Attrezzo("attrezzo2", 5);
		this.stanzaNormale.addAttrezzo(attrezzo2);
		assertEquals(attrezzo2, this.stanzaNormale.getAttrezzo("attrezzo2"));
	}
	
	@Test
	public void testAddAttrezzo_oltreCapienzaLimite() {
		Attrezzo a = new Attrezzo("attrezzo", 1);
		
		for(int i = 0; i < 9; i++) {
			this.stanzaNormale.addAttrezzo(a);
		}
		
		Attrezzo attrezzoNonInseribile = new Attrezzo("nonInseribile", 1);
		assertFalse(this.stanzaNormale.addAttrezzo(attrezzoNonInseribile));
	}
	
	@Test
	public void testRemoveAttrezzo() {
		assertTrue(this.stanzaNormale.removeAttrezzo(attrezzo1));
		assertFalse(this.stanzaNormale.hasAttrezzo("attrezzo1"));
	}
	
	
	@Test
	public void testRemoveAttrezzo_attrezzoInesistente() {
		Attrezzo attrezzoInesistente = new Attrezzo("inesistente", 1);
		assertFalse(this.stanzaNormale.removeAttrezzo(attrezzoInesistente));
	}
	
	@Test
	public void testRemoveAttrezzo_verificaOrdineArrayRistabilito() {
		Attrezzo secondoAttrezzo = new Attrezzo("secondo", 1);
		this.stanzaNormale.addAttrezzo(secondoAttrezzo);
		this.stanzaNormale.removeAttrezzo(this.attrezzo1);
		this.stanzaNormale.hasAttrezzo(secondoAttrezzo.getNome());
	}
	

}
