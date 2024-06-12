package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Mago extends AbstractPersonaggio{
	
	private static final String MESSAGGIO_DONO = "Sei un vero simpaticone (forse), "+
			"con una mia magica azione, troverai un nuovo oggetto per il tuo borsone!";
	
	private static final String MESSAGGIO_SCUSE = "Mi dispiace, non ho più nulla...";
	
	private static final String MESSAGGIO_MAGIA = "Ho compiuto la mia magia, guarda nella stanza!";
	
	private static final String MESSAGGIO_NIENTE_DONO = "Mi spiace, ho già fatto la mia magia!";
	
	
	
	private Attrezzo attrezzo;
	
	private boolean magiaCompiuta;

	public Mago(String nome, String presenzatione, Attrezzo attrezzo) {
		super(nome, presenzatione);
		this.attrezzo = attrezzo;
		this.magiaCompiuta = false;
	}

	@Override
	public String agisci(Partita partita) {
		String messaggio;
		if(this.attrezzo != null) {
			partita.getStanzaCorrente().addAttrezzo(this.attrezzo);
			this.attrezzo = null;
			messaggio = MESSAGGIO_DONO;
		} else {
			messaggio = MESSAGGIO_SCUSE;
		}
		return messaggio;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		if(this.magiaCompiuta) {
			attrezzo.setPeso(attrezzo.getPeso() / 2);
			this.magiaCompiuta = true;
			partita.getStanzaCorrente().addAttrezzo(attrezzo);
			return MESSAGGIO_MAGIA;
		} else {
			return MESSAGGIO_NIENTE_DONO;
		}
		
	}

}
