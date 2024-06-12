package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoFine extends AbstractComando {

	private static final String NOME = "fine";

	public ComandoFine() {
		super.setNome(NOME);
	}

	@Override
	public void esegui(Partita partita) {
		super.getIOConsole().mostraMessaggio("Grazie di aver giocato!");
		partita.setFinita();
	}


}
