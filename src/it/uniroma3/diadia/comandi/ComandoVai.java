package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai extends AbstractComando {
	
	private static final String NOME = "vai";
	
	public ComandoVai() {
		super.setNome(NOME);
	}

	@Override
	public void esegui(Partita partita) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Stanza prossimaStanza = null;
		if(super.getParametro() == null) {
			super.getIOConsole().mostraMessaggio("Dove vuoi andare? \nDevi speficare una direzione!");
			return;
		}
		prossimaStanza = stanzaCorrente.getStanzaAdiacente(super.getParametro());
		if(prossimaStanza == null) {
			super.getIOConsole().mostraMessaggio("Direzione inesistente!");
			return;
		}
		partita.setStanzaCorrente(prossimaStanza);
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
		ComandoGuarda guarda = new ComandoGuarda();
		guarda.setIOConsole(super.getIOConsole());
		guarda.esegui(partita);
			
	}



}
