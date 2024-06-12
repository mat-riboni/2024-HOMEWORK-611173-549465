package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa extends AbstractComando {
	
	private static final String NOME = "posa";

	public ComandoPosa() {
		super.setNome(NOME);
	}

	@Override
	public void esegui(Partita partita) {
		if(super.getParametro() == null) {
			super.getIOConsole().mostraMessaggio("Non hai inserito nessun oggetto da posare!");
		}
		
		
		else if(partita.getGiocatore().getBorsa().hasAttrezzo(super.getParametro())) {
			Attrezzo attrezzoDaPosare = partita.getGiocatore().getBorsa().getAttrezzo(super.getParametro());
			partita.getStanzaCorrente().addAttrezzo(attrezzoDaPosare);
			partita.getGiocatore().getBorsa().removeAttrezzo(super.getParametro());
			super.getIOConsole().mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
			super.getIOConsole().mostraMessaggio(partita.getGiocatore().getBorsa().toString());
		} else {
			super.getIOConsole().mostraMessaggio(super.getParametro() + " non presente nella borsa!");;
			super.getIOConsole().mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		}
	}


}
