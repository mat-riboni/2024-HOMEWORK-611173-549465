package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoGuarda extends AbstractComando {

	private static final String NOME = "guarda";

	public ComandoGuarda() {
		super.setNome(NOME);
	}

	@Override
	public void esegui(Partita partita) {
		super.getIOConsole().mostraMessaggio(partita.getStanzaCorrente().toString());
		super.getIOConsole().mostraMessaggio(partita.getGiocatore().getBorsa().toString());
		if(partita.getStanzaCorrente().getPersonaggio() != null) {
			super.getIOConsole().mostraMessaggio(partita.getStanzaCorrente().getPersonaggio().toString());
		}
		
	}
}
