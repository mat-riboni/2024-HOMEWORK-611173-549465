package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa implements Comando {
	
	private String nomeAttrezzo;

	@Override
	public void esegui(Partita partita) {
		if(nomeAttrezzo == null) {
			System.out.println("Non hai inserito nessun oggetto da posare!");;
		}
		
		
		else if(partita.getGiocatore().getBorsa().hasAttrezzo(nomeAttrezzo)) {
			Attrezzo attrezzoDaPosare = partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
			partita.getStanzaCorrente().addAttrezzo(attrezzoDaPosare);
			partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);
			System.out.println(partita.getStanzaCorrente().getDescrizione());
			System.out.println(partita.getGiocatore().getBorsa().toString());
		} else {
			System.out.println(nomeAttrezzo + " non presente nella borsa!");;
			System.out.println(partita.getStanzaCorrente().getDescrizione());
		}
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;

	}

	@Override
	public String getNome() {
		return "posa";
	}

	@Override
	public String getParametro() {
		return this.nomeAttrezzo;
	}

}
