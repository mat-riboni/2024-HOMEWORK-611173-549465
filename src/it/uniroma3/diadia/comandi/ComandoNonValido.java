package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoNonValido extends AbstractComando{
	
	private static final String NOME = "comando non valido";

	public ComandoNonValido() {
		super.setNome(NOME);
	}

	@Override
	public void esegui(Partita partita) {
		super.getIOConsole().mostraMessaggio("Hai inserito un comando non valido!");

	}

}
