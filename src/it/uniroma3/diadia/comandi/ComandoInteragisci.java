package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoInteragisci extends AbstractComando{

	private static final String MESSAGGIO_CON_CHI = "Con chi dovrei interagire?";
	
	private String messaggio;
		
	@Override
	public void esegui(Partita partita) {
		AbstractPersonaggio personaggio;
		personaggio = partita.getStanzaCorrente().getPersonaggio();
		if(personaggio != null) {
			this.messaggio = personaggio.agisci(partita);
			super.getIOConsole().mostraMessaggio(this.messaggio);
		} else {
			super.getIOConsole().mostraMessaggio(MESSAGGIO_CON_CHI);
		}
		
	}

	public String getMessaggio() {
		return messaggio;
	}

	public void setMessaggio(String messaggio) {
		this.messaggio = messaggio;
	}
	
}
