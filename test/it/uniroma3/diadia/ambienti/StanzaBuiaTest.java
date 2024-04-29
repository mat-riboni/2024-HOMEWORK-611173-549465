package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuiaTest {

	StanzaBuia buia;
	Attrezzo luce;
	
	@BeforeEach
	public void setUp() {
		
		this.buia = new StanzaBuia("buia", "luce");
		this.luce = new Attrezzo("luce", 2);
		
	}
	
	@Test
	public void testGetDescrizione_lucePresente() {
		this.buia.addAttrezzo(luce);
		assertEquals(-1, this.buia.getDescrizione().indexOf("buio"));
	}
	
	@Test
	public void testGetDescrizione_luceNonPresente() {
		assertNotEquals(-1, this.buia.getDescrizione().indexOf("buio"));
	}
	
}
