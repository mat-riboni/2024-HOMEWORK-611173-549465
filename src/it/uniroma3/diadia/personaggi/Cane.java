package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio {
	
	private static final String MESSAGGIO_MORSO = "Aaahia ... sono stato morso! Hai perso 1 CFU";
	private static final String ATTREZZO_PREFERITO = "osso";
	private static final String ATTREZZO_LASCIATO = "chiave";
	private static final String MESSAGGIO_OSSO_MANGIATO = "Gnam gnam... il cane ha mangiato "+ ATTREZZO_PREFERITO +
			"e ha lasciato" + ATTREZZO_LASCIATO;

	public Cane(String nome, String presenzatione) {
		super(nome, presenzatione);
	}

	@Override
	public String agisci(Partita partita) {
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu() - 1);
		return MESSAGGIO_MORSO;
	}
	
	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		if(attrezzo.getNome().equals(ATTREZZO_PREFERITO)) {
			Attrezzo chiave = new Attrezzo(ATTREZZO_LASCIATO, 1);
			partita.getStanzaCorrente().addAttrezzo(chiave);
			return MESSAGGIO_OSSO_MANGIATO;
		} else {
			return this.agisci(partita);
		}
	}

}
