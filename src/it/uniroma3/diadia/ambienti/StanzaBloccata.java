package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccata extends Stanza {

	private String direzioneBloccata;
	private String nomeAttrezzoSbloccante;

	public StanzaBloccata(String nome, String direzioneBloccata, String nomeAttrezzoSbloccante) {
		super(nome);
		this.direzioneBloccata = direzioneBloccata;
		this.nomeAttrezzoSbloccante = nomeAttrezzoSbloccante;
	}

	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		if(this.hasAttrezzo(this.nomeAttrezzoSbloccante))
			return super.getStanzaAdiacente(direzione);
		else if(this.direzioneBloccata != direzione)
			return super.getStanzaAdiacente(direzione);
		else 
			return this;
	}

	@Override
	public String toString() {

		StringBuilder risultato = new StringBuilder();
		risultato.append(this.getNome());
		risultato.append("\nUscite: ");
		for (Direzione d : this.getDirezioni()) {
			String direzione = d.name();
			if (direzione!=null) {
				if(direzione != this.direzioneBloccata)
					risultato.append(" " + direzione);
				else
					risultato.append("direzione bloccata!");
			}
		}
		risultato.append("\nAttrezzi nella stanza: ");
		for (Attrezzo attrezzo : this.getAttrezzi()) {
			if (attrezzo != null) {
				risultato.append(attrezzo.toString()+" ");
			}
		}
		return risultato.toString();
		}
	}
