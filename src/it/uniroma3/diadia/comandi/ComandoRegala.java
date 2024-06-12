package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoRegala extends AbstractComando {
	
	private final static String NOME = "regala";
	private final static String MESSAGGIO_ATTREZZO_NON_PRESENTE = "Non hai questo oggetto nella borsa!";
	
	public ComandoRegala() {
		super.setNome(NOME);
	}

	@Override
	public void esegui(Partita partita) {
		if(partita.getGiocatore().getBorsa().hasAttrezzo(super.getParametro())) {
			String msg = partita.getStanzaCorrente().getPersonaggio().riceviRegalo(
					partita.getGiocatore().getBorsa().getAttrezzo(super.getParametro()),
					partita);
			partita.getGiocatore().getBorsa().removeAttrezzo(super.getParametro());
			super.getIOConsole().mostraMessaggio(msg);
		} else {
			super.getIOConsole().mostraMessaggio(MESSAGGIO_ATTREZZO_NON_PRESENTE);
		}
	}

}
