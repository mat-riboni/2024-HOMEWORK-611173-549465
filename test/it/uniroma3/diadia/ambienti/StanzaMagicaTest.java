package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagicaTest {
	
	
	private StanzaMagica magica;
	private Attrezzo attrezzo1;
	private Attrezzo attrezzo2;
	
	@BeforeEach
	public void setUp() {
		magica = new StanzaMagica("magica", 1);
		attrezzo1 = new Attrezzo("1", 1);
		attrezzo2 = new Attrezzo("2", 2);
	}
	
	@Test
	public void testModificaAttrezzo() {
		Attrezzo nonModificato = this.attrezzo1;
		assertNotEquals(nonModificato.getPeso(), this.magica.modificaAttrezzo(attrezzo1).getPeso());
	}
	
	@Test
	public void testModificaAttrezzo_peso() {
		assertEquals(this.attrezzo1.getPeso()*2,this.magica.modificaAttrezzo(attrezzo1).getPeso());
	}
	
	@Test
	public void testModificaAttrezzo_nomeInvertito() {
		String reverse = new StringBuilder(this.attrezzo1.getNome()).reverse().toString();
		assertEquals(reverse, this.magica.modificaAttrezzo(attrezzo1).getNome());
	}
	
	@Test
	public void testAddAttrezzo_sottoSogliaMagica() {
		assertTrue(this.magica.addAttrezzo(attrezzo2));
	}
	
	@Test
	public void testAddAttrezzo_oltreSogliaMagica() {
		this.magica.addAttrezzo(attrezzo1);
		Attrezzo nonModificato = attrezzo2;
		this.magica.addAttrezzo(attrezzo2);
		assertNotEquals(nonModificato.getPeso(), this.magica.getAttrezzo(this.attrezzo2.getNome()).getPeso());
	}
	
	@Test
	public void testAddAttrezzo_contatoreAttrezziPosatiModificato() {
		this.magica.addAttrezzo(attrezzo1);
		assertNotEquals(0, this.magica.getContatoreAttrezziPosati());
		
	}
}
