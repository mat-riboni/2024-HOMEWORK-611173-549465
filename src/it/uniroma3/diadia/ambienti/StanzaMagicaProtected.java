package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagicaProtected extends StanzaProtected{
	static final private int SOGLIA_MAGICA_DEFAULT = 3;
	private int contatoreAttrezziPosati;
	private int sogliaMagica;

	public StanzaMagicaProtected(String nome, int sogliaMagica) {
		super(nome);
		this.sogliaMagica = sogliaMagica;
		this.contatoreAttrezziPosati = 0;
	}
	
	public StanzaMagicaProtected(String nome) {
		this(nome, SOGLIA_MAGICA_DEFAULT);
	}
	
	public Attrezzo modificaAttrezzo(Attrezzo attrezzo) {
		
		int pesoDoppio = attrezzo.getPeso()*2;
		
		StringBuilder nomeInverso = new StringBuilder(attrezzo.getNome());
		nomeInverso = nomeInverso.reverse();
		attrezzo = new Attrezzo(nomeInverso.toString(), pesoDoppio);
		return attrezzo;	
	}
	
	@Override
	public boolean addAttrezzo(Attrezzo attrezzo) {
		this.contatoreAttrezziPosati++;
		if(this.contatoreAttrezziPosati > this.sogliaMagica) {
			attrezzo = this.modificaAttrezzo(attrezzo);
		}
		if (this.numeroAttrezzi < StanzaProtected.NUMERO_MASSIMO_ATTREZZI) {
        	this.attrezzi[numeroAttrezzi] = attrezzo;
        	this.numeroAttrezzi++;
        	return true;
        }
        else {
        	return false;
        }
	}

	public int getContatoreAttrezziPosati() {
		return contatoreAttrezziPosati;
	}

}
