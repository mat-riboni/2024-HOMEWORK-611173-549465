package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;

public class FabbricaDiComandiRiflessivaTest {

	
	FabbricaDiComandi factory;
	IO io;
	
	@Before
	public void setUp() {
		io = new IOConsole();
		factory = new FabbricaDiComandiRiflessiva(io);
	}
	
	@Test
	public void testEseguiComando_inesistente() {
		assertEquals(new ComandoNonValido().getClass(), this.factory.costruisciComando("comando inesistente").getClass());
	}
	
	
}


