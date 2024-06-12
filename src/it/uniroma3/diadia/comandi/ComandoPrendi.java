package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi extends AbstractComando {
	
	private static final String NOME = "prendi";
	
	public ComandoPrendi() {
		super.setNome(NOME);
	}

	@Override
	public void esegui(Partita partita) {
		if(super.getParametro() == null) {
			super.getIOConsole().mostraMessaggio("Non hai inserito nessun oggetto da prendere!");
		}
		
		
		else if(partita.getStanzaCorrente().hasAttrezzo(super.getParametro())) {
			Attrezzo attrezzoDaPrendere = partita.getStanzaCorrente().getAttrezzo(super.getParametro());
			partita.getGiocatore().getBorsa().addAttrezzo(attrezzoDaPrendere);
			partita.getStanzaCorrente().removeAttrezzo(attrezzoDaPrendere);
			super.getIOConsole().mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
			super.getIOConsole().mostraMessaggio(partita.getGiocatore().getBorsa().toString());
		} else {
			super.getIOConsole().mostraMessaggio(super.getParametro() + " non presente in questa stanza!");;
			super.getIOConsole().mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		}

	}


}
