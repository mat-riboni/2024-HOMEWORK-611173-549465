package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi implements Comando {
	
	private String nomeAttrezzo;

	@Override
	public void esegui(Partita partita) {
		if(nomeAttrezzo == null) {
			System.out.println("Non hai inserito nessun oggetto da prendere!");
		}
		
		
		else if(partita.getStanzaCorrente().hasAttrezzo(nomeAttrezzo)) {
			Attrezzo attrezzoDaPrendere = partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
			partita.getGiocatore().getBorsa().addAttrezzo(attrezzoDaPrendere);
			partita.getStanzaCorrente().removeAttrezzo(attrezzoDaPrendere);
			System.out.println(partita.getStanzaCorrente().getDescrizione());
			System.out.println(partita.getGiocatore().getBorsa().toString());
		} else {
			System.out.println(nomeAttrezzo + " non presente in questa stanza!");;
			System.out.println(partita.getStanzaCorrente().getDescrizione());
		}

	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;

	}

	@Override
	public String getNome() {
		return "prendi";
	}

	@Override
	public String getParametro() {
		return this.nomeAttrezzo;
	}

}
