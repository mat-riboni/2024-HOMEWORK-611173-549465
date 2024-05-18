package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.Set;

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
	public void testAddAttrezzo_oltrePesoMassimo() {
		Attrezzo attrezzoPesante = new Attrezzo("attrezzoPesante", this.borsa.getPesoMax());
		this.borsa.addAttrezzo(attrezzoPesante);
		assertFalse(this.borsa.hasAttrezzo(attrezzoPesante.getNome()));
	}
	
	@Test
	public void testAddAttrezzo_oltreNumeroMassimo() {
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
	public void testRemoveAttrezzo_nonPresente() {
		assertNull(this.borsa.removeAttrezzo("attrezzoCheNonEsiste"));
	}
	
	@Test
	public void testRemoveAttrezzo_attrezzoNelMezzoRimosso() {
		Attrezzo attrezzo2 = new Attrezzo("attrezzo2", 1);
		this.borsa.addAttrezzo(attrezzo2);
		Attrezzo attrezzo3 = new Attrezzo("attrezzo3", 1);
		this.borsa.addAttrezzo(attrezzo3);
		this.borsa.removeAttrezzo(attrezzo2.getNome());
		
		assertEquals(attrezzo3, this.borsa.getAttrezzi().get(1));
	}
	
	@Test
	public void testGetSortedSetOrdinatoPerPeso_stessoPesoDiversoNome() {
		Attrezzo attrezzoStessoPesoF = new Attrezzo("f",1);
		Attrezzo attrezzoStessoPesoZ = new Attrezzo("z",1);
		
		this.borsa.addAttrezzo(attrezzoStessoPesoZ);
		this.borsa.addAttrezzo(attrezzoStessoPesoF);
		
		Set<Attrezzo> setOrdinato = this.borsa.getSortedSetOrdinatoPerPeso();
		Iterator<Attrezzo> it = setOrdinato.iterator();
		assertEquals(this.attrezzo1, it.next());
		assertEquals(attrezzoStessoPesoF, it.next());;
		assertEquals(attrezzoStessoPesoZ, it.next());
	}
	
	@Test
	public void testGetContenutoOrdinatoPerPeso() {
		Attrezzo attrezzo5 = new Attrezzo("a", 5);
		Attrezzo attrezzo3 = new Attrezzo("b", 3);
		this.borsa.addAttrezzo(attrezzo5);
		this.borsa.addAttrezzo(attrezzo3);
		
		Iterator<Attrezzo> it = this.borsa.getContenutoOrdinatoPerPeso().iterator();
		assertEquals(this.attrezzo1, it.next());
		assertEquals(attrezzo3, it.next());
		assertEquals(attrezzo5, it.next());
	}
	
	@Test 
	public void testGetContenutoOrdinatoPerNome() {
		Attrezzo aa = new Attrezzo("aa", 1);
		this.borsa.addAttrezzo(aa);
		Iterator<Attrezzo> it = this.borsa.getContenutoOrdniatoPerNome().iterator();
		assertEquals(aa, it.next());
		assertEquals(this.attrezzo1, it.next());
		
	}
	
	@Test
	public void testGetContenutoRaggruppatoPerPeso() {
		Attrezzo a1 = new Attrezzo("a", 1);
		this.borsa.addAttrezzo(a1);
		assertEquals(1, this.borsa.getContenutoRaggruppatoPerPeso().size());
		assertEquals(2, this.borsa.getContenutoRaggruppatoPerPeso().get(1).size());
	}

}
