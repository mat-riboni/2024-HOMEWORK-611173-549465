package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class BorsaTest {
	
	private Borsa borsa;
	private Attrezzo attrezzo1 = new Attrezzo("attrezzo1", 1);

	@BeforeEach
	void setUp() throws Exception {
		this.borsa = new Borsa();
		this.borsa.addAttrezzo(attrezzo1);
	}

	@Test
	public void testAddAttrezzo() {
		assertTrue(this.borsa.hasAttrezzo(attrezzo1.getNome()));
	}
	
	@Test
	public void testAddAttrezzoOltrePesoMassimo() {
		Attrezzo attrezzoPesante = new Attrezzo("attrezzoPesante", this.borsa.getPesoMax());
		this.borsa.addAttrezzo(attrezzoPesante);
		assertFalse(this.borsa.hasAttrezzo(attrezzoPesante.getNome()));
	}
	
	@Test
	public void testAddAttrezzoOltreNumeroMassimo() {
		Attrezzo attrezzo = new Attrezzo("attrezzo", 0);
		for (int i = 0; i < 9; i++) {
			this.borsa.addAttrezzo(attrezzo);
		}
		Attrezzo attrezzo11Esimo = new Attrezzo("11esimo", 0);
		assertFalse(this.borsa.hasAttrezzo(attrezzo11Esimo.getNome()));
	}
	
	@Test
	public void testRemoveAttrezzo() {
		this.borsa.removeAttrezzo(this.attrezzo1.getNome());
		assertFalse(this.borsa.hasAttrezzo(this.attrezzo1.getNome()));
	}
	
	@Test
	public void testRemoveAttrezzoNonPresente() {
		assertNull(this.borsa.removeAttrezzo("attrezzoCheNonEsiste"));
	}
	
	/*
	 * Verifica se il metodo ripristina il giusto ordine nell'array "attrezzi", ovvero se 
	 * l'array non contiene una casella null tra due caselle non null.
	 */
	@Test
	public void testRemoveAttrezzoRipristinaOrdineArray() {
		Attrezzo attrezzo2 = new Attrezzo("attrezzo2", 1);
		this.borsa.addAttrezzo(attrezzo2);
		Attrezzo attrezzo3 = new Attrezzo("attrezzo3", 1);
		this.borsa.addAttrezzo(attrezzo3);
		this.borsa.removeAttrezzo(attrezzo2.getNome());
		
		assertEquals(attrezzo3, this.borsa.getAttrezzi()[1]);
	}

}
