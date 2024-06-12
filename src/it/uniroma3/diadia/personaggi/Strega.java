package it.uniroma3.diadia.personaggi;

import java.util.Set;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio {
	
	private final static String MESSAGGIO_SE_NON_SALUTATO = "Incontri un personaggio e neanche lo saluti? " +
			"Ti spedirò nel posto peggiore qui vicino!";
	
	private final static String MESSAGGIO_SE_SALUTATO = "Oggi sono magnanima, " + 
			"ti spedisco dove avrai più fortuna!";
	
	private final static String MESSAGGIO_RISATA = "Hahahahaha sciocco, questo riamane a me!";

	public Strega(String nome, String presenzatione) {
		super(nome, presenzatione);
	}

	@Override
	public String agisci(Partita partita) {
		
		Set<Direzione> direzioni = partita.getStanzaCorrente().getDirezioni();
		int min = 0;
		int max = 0;
		String direzioneMin = null;
		String direzioneMax = null;
		for(Direzione dir : direzioni) {
			String d = dir.name();
			Stanza adiacente = partita.getStanzaCorrente().getStanzaAdiacente(d);
			if(adiacente.getAttrezzi().size() > max) {
				max = adiacente.getAttrezzi().size();
				direzioneMax = d;
			}
			if(adiacente.getAttrezzi().size() <= min) {
				min = adiacente.getAttrezzi().size();
				direzioneMin = d;
			}
		}
		if(super.getHaSalutato()) {
			partita.setStanzaCorrente(partita.getStanzaCorrente().getStanzaAdiacente(direzioneMax));
			return MESSAGGIO_SE_SALUTATO;
		} else {
			partita.setStanzaCorrente(partita.getStanzaCorrente().getStanzaAdiacente(direzioneMin));
			return MESSAGGIO_SE_NON_SALUTATO;
		}
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		return MESSAGGIO_RISATA;
	}

}
